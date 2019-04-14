package com.claims.config;

import com.claims.model.Chatbot;
import com.claims.repository.ChatbotRepository;
import com.claims.repository.InMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
public class ApplicationConfig {

    @Autowired
    private ChatbotRepository chatbotRepository;

    @Autowired
    private InMemoryService inMemoryService;

    @PostConstruct
    public void init(){
        Collection<Chatbot> chatBot = chatbotRepository.findAll();
        for (Chatbot allName : chatBot) {
            this.inMemoryService.addMessage(allName.getName().toLowerCase(),allName.getDescription());
        }
    }
}
