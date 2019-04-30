package com.claims.config;

import com.claims.model.Chatbot;
import com.claims.model.ClusterGroup;
import com.claims.model.MemberDetails;
import com.claims.model.User;
import com.claims.repository.ChatbotRepository;
import com.claims.repository.InMemoryService;
import com.claims.repository.UserRepository;
import com.claims.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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
        loadClusters();
    }

    private void loadUsers() {
        userRepository.deleteAll();
        userRepository.save(new User("admin", userService.encrypt("admin"), "admin"));
        userRepository.save(new User("preethi", userService.encrypt("preethi123"), "user"));
        userRepository.save(new User("praveen", userService.encrypt("praveen123"), "user"));
        userRepository.save(new User("sanjay", userService.encrypt("sanjay123"), "user"));
        userRepository.save(new User("karthik", userService.encrypt("karthik123"), "user"));

    }

    private void loadClusters() {
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("MemberDetails.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
            String memberData = null;
            Map<String, String> memberDataMap = new HashMap<>();
            while ((memberData = br.readLine()) != null) {
                String memberId = memberData.split(",")[0];
                memberDataMap.put(memberId.trim(), memberData);
            }
            resourceAsStream = getClass().getClassLoader().getResourceAsStream("cluster_groups");
            br = new BufferedReader(new InputStreamReader(resourceAsStream));
            String clusterLine = null;
            String group = "Group-";
            int i = 0;
            List<ClusterGroup> clusterGroups = new ArrayList<>();
            List<ClusterGroup> clusterGroups1 = new ArrayList<>();
            while ((clusterLine = br.readLine()) != null) {
                String clusterData = clusterLine.substring(clusterLine.indexOf("[") + 1).replace("])", "");
                String[] split = clusterData.split(",");
                List<MemberDetails> memberDetailsList = new ArrayList<>();
                List<MemberDetails> memberDetailsList1 = new ArrayList<>();
                Set<String> memberId=new HashSet<>();
                for (String cluster : split) {
                    if(memberDataMap.get(cluster.trim()) != null) {
                        String clusterMemberData = memberDataMap.get(cluster.trim());
                        String[] split1 = clusterMemberData.split(",");
                        MemberDetails memberDetails = new MemberDetails(split1[0], split1[1], split1[2].equals("1") ? "male" : "female", split1[3]);
                        memberDetailsList.add(memberDetails);
                        if(!memberId.contains(split1[0])){
                            memberId.add(split1[0]);
                            memberDetailsList1.add(memberDetails);
                        }
                    }else{
                        System.out.println(cluster.trim());
                    }
                }
                ClusterGroup clusterGroup = new ClusterGroup();
                clusterGroup.setGroupName(group + (++i));
                clusterGroup.setMemberDetails(memberDetailsList);
                clusterGroups.add(clusterGroup);

                ClusterGroup clusterGroup1 = new ClusterGroup();
                clusterGroup1.setGroupName(group + (i));
                clusterGroup1.setMemberDetails(memberDetailsList1);
                clusterGroups1.add(clusterGroup1);
            }
            inMemoryService.setClusterGroups(clusterGroups);
            inMemoryService.setUniqueClusterGroups(clusterGroups1);
            br.close();
            resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
