package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.UserD;

import java.util.Date;

public class User extends UserD {
    public String id;
    public Date createDate;

    public User(UserD userD, String id) {
        this.name = userD.name;
        this.id = id;
        this.createDate = new Date(userD.createDate);
    }
}
