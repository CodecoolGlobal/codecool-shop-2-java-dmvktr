package com.codecool.shop.model;

import com.codecool.shop.util.PasswordHasher;

public class User extends BaseModel {

    byte[] hashedPassword;

    public User(String name, byte[] hashedPassword) {
        super(name);
        this.hashedPassword = hashedPassword;
    }

    public User(String name, String password) {
        super(name);
        hashedPassword = PasswordHasher.generateHash(password);
    }


}
