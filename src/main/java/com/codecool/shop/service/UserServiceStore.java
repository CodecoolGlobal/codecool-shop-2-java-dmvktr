package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class UserServiceStore {

    private static UserService userService;

    public static void initialize() {
        if (userService == null) {
            userService = new UserService(UserDaoMem.getInstance());
        }
    }

    public static void initialize(DataSource dataSource) {
        if (userService == null) {
            userService = new UserService(UserDaoJDBC.getInstance(dataSource));
        }
    }

    public static UserService get() {
        return userService;
    }

}
