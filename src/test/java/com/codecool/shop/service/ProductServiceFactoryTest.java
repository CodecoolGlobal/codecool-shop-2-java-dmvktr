package com.codecool.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ProductServiceFactoryTest {

    @BeforeEach
    void setUp() {
        ProductServiceFactory.initialize();
    }

    @Test
    void get_onMethodCall_returnsProductService() {
        Assertions.assertSame(ProductServiceFactory.get().getClass(), ProductService.class);
    }

}
