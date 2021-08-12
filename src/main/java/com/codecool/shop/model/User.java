package com.codecool.shop.model;

import java.util.ArrayList;

public class User extends BaseModel {

    byte[] hashedPassword;

    public User(String name, byte[] hashedPassword) {
        super(name);
        this.hashedPassword = hashedPassword;
    }


}
