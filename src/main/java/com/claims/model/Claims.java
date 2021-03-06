package com.claims.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "claims")
@NamedStoredProcedureQueries({
     @NamedStoredProcedureQuery(name = "sp_chatbot",
                procedureName = "sp_chatbot",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "description", type = String.class)
                })

})
public class Claims {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MemberID")
    private Integer memberId;

    @Column(name = "ProviderID")
    private Integer providerId;

    @Column(name = "vendor")
    private Integer vendor;

    @Column(name = "pcp")
    private Integer pcp;

    @Column(name = "Year")
    private String year;

    @Column(name = "specialty")
    private String speciality;

    @Column(name = "placesvc")
    private String placeSvc;

    @Column(name = "paydelay")
    private Integer payDelay;

    @Column(name = "LengthOfStay")
    private String lengthOfStay;

    @Column(name = "dsfs")
    private String dsfs;

    @Column(name = "\"PrimaryConditionGroup\"")
    private String primaryConditionGroup;

    @Column(name = "CharlsonIndex")
    private String charlsonIndex;

    @Column(name="CreatedAt")
    private Date createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getVendor() {
        return vendor;
    }

    public void setVendor(Integer vendor) {
        this.vendor = vendor;
    }

    public Integer getPcp() {
        return pcp;
    }

    public void setPcp(Integer pcp) {
        this.pcp = pcp;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPlaceSvc() {
        return placeSvc;
    }

    public void setPlaceSvc(String placeSvc) {
        this.placeSvc = placeSvc;
    }

    public Integer getPayDelay() {
        return payDelay;
    }

    public void setPayDelay(Integer payDelay) {
        this.payDelay = payDelay;
    }

    public String getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(String lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public String getDsfs() {
        return dsfs;
    }

    public void setDsfs(String dsfs) {
        this.dsfs = dsfs;
    }

    public String getPrimaryConditionGroup() {
        return primaryConditionGroup;
    }

    public void setPrimaryConditionGroup(String primaryConditionGroup) {
        this.primaryConditionGroup = primaryConditionGroup;
    }

    public String getCharlsonIndex() {
        return charlsonIndex;
    }

    public void setCharlsonIndex(String charlsonIndex) {
        this.charlsonIndex = charlsonIndex;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
