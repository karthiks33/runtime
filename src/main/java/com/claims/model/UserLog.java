package com.claims.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_log")
public class UserLog {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "activity")
    private String activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
