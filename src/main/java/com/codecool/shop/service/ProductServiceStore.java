package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.*;

import javax.sql.DataSource;

public class ProductServiceStore {

    private static ProductService productService;

    public static void initialize() {
        if (productService == null) {
            productService = new ProductService(new ProductDaoMem(), new ProductCategoryDaoMem(), new SupplierDaoMem());
        }
    }

    public static void initialize(DataSource dataSource) {
        if (productService == null) {
            productService = new ProductService(new ProductDaoJDBC(dataSource), new ProductCategoryDaoJDBC(dataSource), new SupplierDaoJDBC(dataSource));
        }
    }

    public static ProductService get() {
        return productService;
    }
}
