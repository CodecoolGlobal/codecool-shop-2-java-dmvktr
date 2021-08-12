package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation.UserDaoJDBC;

import javax.sql.DataSource;

public class UserService {

    private UserDao userDao;
    private DataSource dataSource;



    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService(UserDao userDao, DataSource dataSource) {
        this.userDao = userDao;
        this.dataSource = dataSource;
        setDataSourceForDaos();
    }

    private void setDataSourceForDaos() {
        ((UserDaoJDBC) userDao).setDataSource(dataSource);
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
