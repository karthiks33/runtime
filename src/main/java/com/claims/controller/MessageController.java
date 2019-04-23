package com.claims.controller;

import com.claims.model.Result;
import com.claims.repository.ClaimsRepository;
import com.claims.repository.InMemoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private InMemoryService inMemoryService;

    @Autowired
    private ClaimsRepository claimsRepository;

    @RequestMapping(value = "/chat/{question}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getChatReply(@PathVariable String question) {
        String message = "Currently this question is not supported.Please try some other question";
        try {
            logger.info("Getting chat message for  {}", question);
            String[] questionArray = question.split(" ");
            String quest="";
            for (String questionKey : questionArray) {
                if(inMemoryService.getChatBotMessages().get(questionKey) != null){
                    quest=questionKey;
                    break;
                }
            }
            String response = claimsRepository.callChatBot(quest);
            if (!StringUtils.isEmpty(response)) {
                message = response;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Result(message), HttpStatus.OK);
    }
}
