package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.UserD;

import java.util.Date;

public class User extends UserD {
    public Date createDate;

    public User(UserD userD) {
        this.name = userD.name;
        this.createDate = new Date(userD.createDate);
    }
}
