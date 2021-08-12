package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMem {

    private List<User> data = new ArrayList<>();
    private static UserDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private UserDaoMem() {
    }

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }
}
