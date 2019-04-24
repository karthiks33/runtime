package com.claims.config;

import com.claims.model.Chatbot;
import com.claims.model.User;
import com.claims.repository.ChatbotRepository;
import com.claims.repository.InMemoryService;
import com.claims.repository.UserRepository;
import com.claims.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.util.Collection;

@Component
public class ApplicationConfig {

    @Autowired
    private ChatbotRepository chatbotRepository;

    @Autowired
    private InMemoryService inMemoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        Collection<Chatbot> chatBot = chatbotRepository.findAll();
        for (Chatbot allName : chatBot) {
            this.inMemoryService.addMessage(allName.getName().toLowerCase(), allName.getDescription());
        }
        loadUsers();
    }

    private void loadUsers(){
        userRepository.deleteAll();
        userRepository.save(new User("admin",userService.encrypt("admin"),"admin"));
        userRepository.save(new User("preethi",userService.encrypt("preethi123"),"user"));
        userRepository.save(new User("praveen",userService.encrypt("praveen123"),"user"));
        userRepository.save(new User("sanjay",userService.encrypt("sanjay123"),"user"));
        userRepository.save(new User("karthik",userService.encrypt("karthik123"),"user"));

    }
}
