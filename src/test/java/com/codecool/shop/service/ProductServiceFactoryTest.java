package com.codecool.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ProductServiceFactoryTest {

    ProductServiceFactory productServiceFactory = new ProductServiceFactory();

    @Test
    void get_onMethodCall_returnsProductService() {
        Assertions.assertSame(productServiceFactory.get().getClass(), ProductService.class);
    }

}
