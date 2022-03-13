package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.UserD;

import java.util.Date;

public class User extends UserD {
    public String id;
    public Date creationDate;

    public User(UserD userD, String id) {
        this.name = userD.name;
        this.email = userD.email;
        this.id = id;
        this.creationDate = new Date(userD.createDate);
        this.createDate = userD.createDate;
    }
}
