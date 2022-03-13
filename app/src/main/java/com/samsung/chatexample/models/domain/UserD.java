package com.samsung.chatexample.models.domain;

public class UserD {
    public String name;
    public String email;
    public long createDate;

    public UserD() {
    }

    public UserD(String name, String email, long createDate) {
        this.name = name;
        this.email = email;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
