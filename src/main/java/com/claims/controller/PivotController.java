package com.claims.controller;

import com.claims.repository.pivot.OnCharlsonPivotRepository;
import com.claims.repository.pivot.OnConditionPivotRepository;
import com.claims.repository.pivot.OnStayPivotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PivotController {

    private static final Logger logger = LoggerFactory.getLogger(PivotController.class);

    @Autowired
    private OnCharlsonPivotRepository onCharlsonPivotRepository;

    @Autowired
    private OnConditionPivotRepository onConditionPivotRepository;

    @Autowired
    private OnStayPivotRepository onStayPivotRepository;

    @RequestMapping(value = "/getOnCharlsonPivot", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getOnCharlsonPivot() {
        try {
            logger.info("Getting getOnCharlsonPivot");
            return new ResponseEntity<>(onCharlsonPivotRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/getOnConditionPivot", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getOnConditionPivot() {
        try {
            logger.info("Getting getOnConditionPivot");
            return new ResponseEntity<>(onConditionPivotRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/getOnStayPivot", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getOnStayPivot() {
        try {
            logger.info("Getting getOnStayPivot");
            return new ResponseEntity<>(onStayPivotRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex + " <== error", HttpStatus.BAD_REQUEST);
        }
    }

}
