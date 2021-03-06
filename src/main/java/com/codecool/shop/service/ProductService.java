package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        ProductCategory category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getProductsForSuppliers(List<Integer> supplierIDs) {
        List<Supplier> suppliers = new ArrayList<>();
        for (int id : supplierIDs) {
            suppliers.add(supplierDao.find(id));
        }
        List<Product> products = new ArrayList<>();
        for (Supplier supplier: suppliers) {
            List<Product> productsBySupplier = productDao.getBy(supplier);
            products.addAll(productsBySupplier);
        }
        return products;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

}