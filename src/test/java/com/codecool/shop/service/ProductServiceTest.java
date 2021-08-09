package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ProductServiceTest {

    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    OrderDao orderDao;
    ProductService productService;

    @BeforeEach
    void setUp() {
        productDao = mock(ProductDao.class);
        productCategoryDao = mock(ProductCategoryDao.class);
        supplierDao = mock(SupplierDao.class);
        orderDao = mock(OrderDao.class);
        productService = new ProductService(productDao, productCategoryDao, supplierDao, orderDao);
    }

    @Test
    void getProductCategory_onMethodCall_callsProductCategoryDaoFind() {
//        ProductCategory sampleProductCategory = new ProductCategory("test", "test", "test");
//        when(productCategoryDao.find(Mockito.anyInt())).thenReturn(sampleProductCategory);
        ProductCategory productCategory = productService.getProductCategory(1);
        verify(productCategoryDao).find(Mockito.anyInt());
    }
}