package com.claims.repository;

import com.claims.model.Chatbot;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ChatbotRepository extends CrudRepository<Chatbot, Long> {

    Collection<Chatbot> findAll();

}
