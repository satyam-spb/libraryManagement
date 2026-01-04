package com.springTutorial.libraryManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column(nullable = false)
    private String userName;

    // JPA (Hibernate) requires a no-argument constructor (a constructor with no parameters) for every
    // entity. It uses this to create an empty object before filling it with data from the database.
    public User(){}

    //getters and setters
    public long getUserId(){
        return user_id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    
}
