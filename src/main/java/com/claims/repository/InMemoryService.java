package com.claims.repository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InMemoryService {

    private Map<String,String> chatBotMessages=new HashMap<>();

    public Map<String, String> getChatBotMessages() {
        return chatBotMessages;
    }

    public void addMessage(String name,String description) {
        this.chatBotMessages.put(name,description);
    }
}
