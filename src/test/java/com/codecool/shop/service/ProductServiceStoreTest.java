package com.codecool.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class ProductServiceStoreTest {

    @BeforeEach
    void setUp() {
        ProductServiceStore.initialize(mock(ProductService.class));
    }

    @Test
    void get_onMethodCall_returnsProductService() {
        Assertions.assertSame(ProductServiceStore.get().getClass(), ProductService.class);
    }

}
