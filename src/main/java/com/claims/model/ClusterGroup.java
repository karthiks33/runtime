package com.claims.model;

import java.util.List;

public class ClusterGroup {

    private String groupName;

    private List<MemberDetails> memberDetails;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<MemberDetails> getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(List<MemberDetails> memberDetails) {
        this.memberDetails = memberDetails;
    }
}
