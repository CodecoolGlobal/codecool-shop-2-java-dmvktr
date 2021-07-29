package com.codecool.shop.model;

import java.math.BigDecimal;

public class LineItem {

    private Product product;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public BigDecimal getSubtotal() {
        return product.getDefaultPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void updateQuantity(int quantityDiff) {
        quantity += quantityDiff;
    }

    public boolean isQuantityZero() {
        return quantity == 0;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
