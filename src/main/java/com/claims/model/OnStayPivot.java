package com.claims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OnStayPivot")
public class OnStayPivot {

    @Id
    @Column(name = "LengthOfStay")
    private String lengthOfStay;

    @Column(name = "RiskACnt")
    private Double riskACnt;

    @Column(name = "RiskBCnt")
    private Double riskBCnt;

    @Column(name = "RiskCCnt")
    private Double riskCCnt;

    @Column(name = "RiskDCnt")
    private Double riskDCnt;

    public String getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(String lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public Double getRiskACnt() {
        if(riskACnt == null) riskACnt=0.0;
        return riskACnt;
    }

    public void setRiskACnt(Double riskACnt) {
        this.riskACnt = riskACnt;
    }

    public Double getRiskBCnt() {
        if(riskBCnt == null) riskBCnt=0.0;
        return riskBCnt;
    }

    public void setRiskBCnt(Double riskBCnt) {
        this.riskBCnt = riskBCnt;
    }

    public Double getRiskCCnt() {
        if(riskCCnt == null) riskCCnt=0.0;
        return riskCCnt;
    }

    public void setRiskCCnt(Double riskCCnt) {
        this.riskCCnt = riskCCnt;
    }

    public Double getRiskDCnt() {
        if(riskDCnt == null) riskDCnt=0.0;
        return riskDCnt;
    }

    public void setRiskDCnt(Double riskDCnt) {
        this.riskDCnt = riskDCnt;
    }
}
