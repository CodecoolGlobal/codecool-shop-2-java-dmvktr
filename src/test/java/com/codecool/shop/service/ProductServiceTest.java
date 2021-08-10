package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    OrderDao orderDao;
    ProductService productService;
    ProductCategory sampleProductCategory;

    @BeforeEach
    void setUp() {
        productDao = mock(ProductDao.class);
        productCategoryDao = mock(ProductCategoryDao.class);
        supplierDao = mock(SupplierDao.class);
        orderDao = mock(OrderDao.class);
        productService = new ProductService(productDao, productCategoryDao, supplierDao);
        sampleProductCategory = new ProductCategory("test", "test", "test");
    }

    @Test
    void getProductCategory_onMethodCall_callsProductCategoryDaoFind() {
        productService.getProductCategory(1);
        verify(productCategoryDao).find(Mockito.anyInt());
    }

    @Test
    void getProductsForCategory_onMethodCall_callsProductCategoryDaoFind() {
        productService.getProductsForCategory(1);
        verify(productCategoryDao).find(Mockito.anyInt());
    }

    @Test
    void getProductsForCategory_onValidProductCategory_returnsListOfProducts() {
        ProductCategory tShirt = new ProductCategory("T-Shirt", "Apparel", "A t-shirt commonly shortened to tee, is awesome.");
        when(productCategoryDao.find(Mockito.anyInt())).thenReturn(tShirt);
        Supplier coolStuff = new Supplier("Cool Stuff", "Your T-shirts, customized");
        when(productDao.getBy(tShirt)).thenReturn(new ArrayList<>(List.of(new Product("GitHub Half Sleeve Unisex T-Shirt",
                new BigDecimal("21.9"), "EUR", "The cloth belt is mostly decorative and a sign of wealth.", tShirt, coolStuff,
                "CoolCode_GitHub.jpeg", "CoolCode_GitHub_hover.jpeg"))));
        List<Product> sampleProducts = productService.getProductsForCategory(1);
        assertEquals(1, sampleProducts.size());
    }

    @Test
    void getProductsForCategory_onInvalidProductCategory_returnsEmptyList() {
        when(productCategoryDao.find(Mockito.anyInt())).thenReturn(null);
        List<Product> sampleProducts = productService.getProductsForCategory(1);
        assertEquals(0, sampleProducts.size());
    }
}