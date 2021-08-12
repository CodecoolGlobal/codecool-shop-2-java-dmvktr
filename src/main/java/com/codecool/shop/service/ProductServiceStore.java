package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;

import javax.sql.DataSource;

public class ProductServiceStore {

    private static ProductService productService;

    public static void initialize(ProductService productServiceObj) {
        if (productService == null) {
            productService = productServiceObj;
        }
    }

    public static ProductService get() {
        return productService;
    }
}
