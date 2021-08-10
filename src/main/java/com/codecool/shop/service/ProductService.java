package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class ProductService{
    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    OrderDao orderDao;
    DataSource dataSource;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
        this.orderDao = orderDao;
    }

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, OrderDao orderDao, DataSource dataSource) {
        this.dataSource = dataSource;
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
        this.orderDao = orderDao;
        setDataSourceForDaos();
    }

    private void setDataSourceForDaos() {
        ((ProductDaoJDBC) productDao).setDataSource(dataSource);
        ((ProductCategoryDaoJDBC) productCategoryDao).setDataSource(dataSource);
        ((SupplierDaoJDBC) supplierDao).setDataSource(dataSource);
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
            for (Product product : productsBySupplier) {
                products.add(product);
            }
        }
        return products;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}