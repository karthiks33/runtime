package com.claims.repository;

import com.claims.model.ClusterGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private List<ClusterGroup> clusterGroups=new ArrayList<>();

    private List<ClusterGroup> uniqueClusterGroups=new ArrayList<>();

    public List<ClusterGroup> getClusterGroups() {
        return clusterGroups;
    }

    public void setClusterGroups(List<ClusterGroup> clusterGroups) {
        this.clusterGroups = clusterGroups;
    }

    public List<ClusterGroup> getUniqueClusterGroups() {
        return uniqueClusterGroups;
    }

    public void setUniqueClusterGroups(List<ClusterGroup> uniqueClusterGroups) {
        this.uniqueClusterGroups = uniqueClusterGroups;
    }
}
