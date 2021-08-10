package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.*;

import javax.sql.DataSource;

public class ProductServiceFactory {

    private static ProductServiceFactory instance = null;
    private static ProductService productService;

    private ProductServiceFactory() {
        productService = new ProductService(ProductDaoMem.getInstance(), ProductCategoryDaoMem.getInstance(), SupplierDaoMem.getInstance(), OrderDaoMem.getInstance());
    }

    private ProductServiceFactory(DataSource dataSource) {
        productService = new ProductService(ProductDaoJDBC.getInstance(), ProductCategoryDaoJDBC.getInstance(), SupplierDaoJDBC.getInstance(), OrderDaoMem.getInstance(), dataSource);
    }

    public static ProductServiceFactory initialize() {
        if (instance == null) {
            instance = new ProductServiceFactory();
        }
        return instance;
    }

    public static ProductServiceFactory initialize(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductServiceFactory(dataSource);
        }
        return instance;
    }

    public static ProductService getProductService() {
        return productService;
    }
}
