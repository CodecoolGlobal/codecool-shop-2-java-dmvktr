package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceMemTest {

    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    OrderDao orderDao;
    ProductService productServiceMem;
    ProductCategory sampleProductCategory;

    @BeforeEach
    void setUp() {
        productDao = mock(ProductDaoMem.class);
        productCategoryDao = mock(ProductCategoryDaoMem.class);
        supplierDao = mock(SupplierDaoMem.class);
        orderDao = mock(OrderDao.class);
        productServiceMem = new ProductService(productDao, productCategoryDao, supplierDao);
        sampleProductCategory = new ProductCategory("test", "test", "test");
    }

    @Test
    void getProductCategory_onMethodCall_callsProductCategoryDaoFind() {
        productServiceMem.getProductCategory(1);
        verify(productCategoryDao).find(Mockito.anyInt());
    }

    @Test
    void getProductsForCategory_onMethodCall_callsProductCategoryDaoFind() {
        productServiceMem.getProductsForCategory(1);
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
        List<Product> sampleProducts = productServiceMem.getProductsForCategory(1);
        assertEquals(1, sampleProducts.size());
    }

    @Test
    void getProductsForCategory_onInvalidProductCategory_returnsEmptyList() {
        when(productCategoryDao.find(Mockito.anyInt())).thenReturn(null);
        List<Product> sampleProducts = productServiceMem.getProductsForCategory(1);
        assertEquals(0, sampleProducts.size());
    }

    @Test
    void getProductsForSuppliers_onMethodCall_callsSupplierDaoFind() {
//
//        when(productCategoryDao.find(Mockito.anyInt())).thenReturn(sampleProductCategory);
        List<Integer> supplierIDs = new ArrayList<>(Arrays.asList(0, 1, 2));
        productServiceMem.getProductsForSuppliers(supplierIDs);
        verify(supplierDao, times(3)).find(Mockito.anyInt());
    }

    @Test
    void getProductsForSuppliers_onInvalidSupplierID_returnsEmptyList() {
        List<Integer> supplierIDs = new ArrayList<>(Arrays.asList(200));
        Supplier testSupplier1 = mock(Supplier.class);
        when(supplierDao.find(1)).thenReturn(testSupplier1);
        List<Product> testSuppliers = productServiceMem.getProductsForSuppliers(supplierIDs);
        assertEquals(0, testSuppliers.size());
    }

    @Test
    void getProductsForSuppliers_onValidProductSupplierID_returnsNotEmptyListOfProducts() {
        List<Integer> supplierIDs = new ArrayList<>(Arrays.asList(0, 1, 2));
        Supplier testSupplier = new Supplier("Zara", "Croatian");
        List<Product> testProductList = new ArrayList<>(Arrays.asList(mock(Product.class), mock(Product.class)));
        when(supplierDao.find(Mockito.anyInt())).thenReturn(testSupplier);
        when(productDao.getBy(testSupplier)).thenReturn(testProductList);
        assertTrue(productServiceMem.getProductsForSuppliers(supplierIDs).size() > 0);
    }
}