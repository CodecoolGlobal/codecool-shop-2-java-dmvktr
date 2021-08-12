package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Objects;

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return Objects.equals(getProduct().getId(), lineItem.getProduct().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct());
    }
}