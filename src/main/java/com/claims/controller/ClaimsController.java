package com.claims.controller;

import com.claims.model.Claims;
import com.claims.model.Result;
import com.claims.model.User;
import com.claims.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
public class ClaimsController {

    private static final Logger logger = LoggerFactory.getLogger(ClaimsController.class);

    @Autowired
    private ClaimsRepository claimsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AudiRepository audiRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private InMemoryService inMemoryService;

    @GetMapping("/test")
    public String testService() {
        return "success";
    }

    //Claims CRUD
    @RequestMapping(value = "/listClaims", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllClaims() {
        try {
            logger.info("Getting all claims");
            return new ResponseEntity<>(claimsRepository.findLatestClaims(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete/{claimId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteClaim(@PathVariable Long claimId) {
        try {
            logger.info("Deleting claim {} ", claimId);
            if (claimNotExists(claimId)) {
                return new ResponseEntity<>(new Result("Claim id does not exists"), HttpStatus.NOT_ACCEPTABLE);
            }
            claimsRepository.deleteById(claimId);
            return new ResponseEntity<>(new Result("Claim id " + claimId + " deleted successfully"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveClaim(@RequestBody Claims claims) {
        try {
            logger.info("Saving claims");
            if (claims.getId() != null) {
                logger.error("Claim id is present and cannot be saved");
                return new ResponseEntity<>(new Result("Claim id should be null"), HttpStatus.NOT_ACCEPTABLE);
            }
            claims.setCreatedAt(new Date());
            Claims savedClaims = claimsRepository.save(claims);
            return new ResponseEntity<>(new Result("Saved claims successfully with id " + savedClaims.getId()), HttpStatus.OK);
        } catch (Exception ex) {
            return getResponseSaveUpdateEntity(claims, ex);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateClaim(@RequestBody Claims claims) {
        try {
            logger.info("Update claims");
            claims.setCreatedAt(new Date());
            if (claimNotExists(claims.getId())) {
                return new ResponseEntity<>(new Result("Claim id should not be null"), HttpStatus.NOT_ACCEPTABLE);
            }
            Claims savedClaims = claimsRepository.save(claims);
            return new ResponseEntity<>(new Result("Claims updated successfully with id " + savedClaims.getId()), HttpStatus.OK);
        } catch (Exception ex) {
            return getResponseSaveUpdateEntity(claims, ex);
        }
    }

    private ResponseEntity<?> getResponseSaveUpdateEntity(@RequestBody Claims claims, Exception ex) {
        String errorMessage;
        errorMessage = ex + " <== error";
        if (errorMessage.contains("ConstraintViolationException")) {
            errorMessage = String.format("Constraint Violation.ProviderID %s does not exist", claims.getProviderId());
        }
        return new ResponseEntity<>(new Result(errorMessage), HttpStatus.BAD_REQUEST);
    }

    private boolean claimNotExists(@RequestBody Long claimId) {
        if (claimId == null) {
            logger.error("Claim id is not present and cannot be updated");
            return true;
        }
        if (claimsRepository.findClaimsById(claimId.intValue()).isEmpty()) {
            logger.error("Claim id is not present and cannot be updated");
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/getClaim/{claimType}/{claimValue}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getClaim(@PathVariable String claimType, @PathVariable Integer claimValue) {
        try {
            logger.info("Getting all claims for claimType {} and claimValue {}", claimType, claimValue);
            if (StringUtils.isEmpty(claimType) || StringUtils.isEmpty(claimValue)) {
                return new ResponseEntity<>("Claim type and claim value cannot be null", HttpStatus.NOT_ACCEPTABLE);
            }
            if (claimType.equals("memberId")) {
                return new ResponseEntity<>(claimsRepository.findClaimsByMemberId(claimValue), HttpStatus.OK);
            } else if (claimType.equals("claimsId")) {
                Collection<Claims> claimsById = claimsRepository.findClaimsById(claimValue);
                System.out.println(new ObjectMapper().writeValueAsString(claimsById));
                return new ResponseEntity<>(claimsRepository.findClaimsById(claimValue), HttpStatus.OK);
            } else if (claimType.equals("vendorId")) {
                return new ResponseEntity<>(claimsRepository.findClaimsByVendorId(claimValue), HttpStatus.OK);
            } else if (claimType.equals("providerId")) {
                return new ResponseEntity<>(claimsRepository.findClaimsByProviderId(claimValue), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Result("No valid claim found"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> checkLoginUser(@RequestBody User user) {
        try{
            for (User entityUser : userRepository.findAll()) {
                if(entityUser.getUname().equals(user.getUname()) && userService.decrypt(entityUser.getPwd()).equals(user.getPwd())){
                    return new ResponseEntity<>(new Result("Success"), HttpStatus.OK);
                }
            }
        }catch (Exception ex) {
            return new ResponseEntity<>(new Result("Failure"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result("Failure"), HttpStatus.OK);
    }

    @RequestMapping(value = "/getLogs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getLogs() {
        try {
            return new ResponseEntity<>(audiRepository.getLogs(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getClusterGroups", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getCluster() {
        try {
            return new ResponseEntity<>(inMemoryService.getClusterGroups(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getUniqueClusterGroups", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getUniqueCluster() {
        try {
            return new ResponseEntity<>(inMemoryService.getUniqueClusterGroups(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }
}
