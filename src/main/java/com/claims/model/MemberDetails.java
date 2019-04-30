package com.claims.model;

public class MemberDetails {

    private String memberId;

    private String riskLevel;

    private String gender;

    private String age;

    public MemberDetails(){

    }

    public MemberDetails(String memberId,String riskLevel,String gender,String age){
        this.memberId=memberId;
        this.riskLevel=riskLevel;
        this.gender=gender;
        this.age=age;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
