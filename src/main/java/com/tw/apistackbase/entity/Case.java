package com.tw.apistackbase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Case {

    @Id
    private String id= UUID.randomUUID().toString();

    @Column(length = 255,nullable=false)
    private String name;

    @Column(nullable = false)
    private Long time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }
}
