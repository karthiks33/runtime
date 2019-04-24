package com.claims.model;

import javax.persistence.*;

@Entity
@Table(name = "claims_user")
public class User {

    @Id
    @Column(name = "uname")
    private String uname;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "role")
    private String role;

    public User(){

    }
    public User(String uname,String pwd,String role){
        this.uname=uname;
        this.pwd=pwd;
        this.role=role;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
