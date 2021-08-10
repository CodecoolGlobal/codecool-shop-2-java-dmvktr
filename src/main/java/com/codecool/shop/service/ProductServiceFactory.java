package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.*;

import javax.sql.DataSource;

public class ProductServiceFactory {

    private static ProductService productService;

    public static void initialize() {
        if (productService == null) {
            productService = new ProductService(ProductDaoMem.getInstance(), ProductCategoryDaoMem.getInstance(), SupplierDaoMem.getInstance());
        }
    }

    public static void initialize(DataSource dataSource) {
        if (productService == null) {
            productService = new ProductService(ProductDaoJDBC.getInstance(), ProductCategoryDaoJDBC.getInstance(), SupplierDaoJDBC.getInstance(), dataSource);
        }
    }

    public static ProductService get() {
        return productService;
    }
}
