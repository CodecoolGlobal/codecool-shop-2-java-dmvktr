package com.codecool.shop.model;

import java.math.BigDecimal;

public class LineItem {

    private Product product;
    private int quantity;
    private BigDecimal subTotalPrice;

    public int getQuantity() {
        return quantity;
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        refreshSubTotalPrice();
    }

    public Product getProduct() {
        return this.product;
    }

    public BigDecimal getSubTotalPrice() {
        refreshSubTotalPrice();
        return subTotalPrice;
    }

    public void updateQuantity(int quantityDiff) {
        quantity += quantityDiff;
        refreshSubTotalPrice();
    }

    private void refreshSubTotalPrice() {
        subTotalPrice = product.getDefaultPrice().multiply(BigDecimal.valueOf(quantity));
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
