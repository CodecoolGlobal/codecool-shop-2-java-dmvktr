package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderID;
    private int userID;
    private List<LineItem> items = new ArrayList<>();
    private BigDecimal totalPrice;
    private int itemCount;
    private CheckoutDetails checkoutDetails = null;


    public Order(int userID) {
        this.userID = userID;
//        refreshTotalPrice();
    }

    private void addProduct(Product product, int quantity) {
        for (LineItem item : items) {
            if (isProductInItem(product, item)) {
                item.updateQuantity(quantity);
//                refreshTotalPrice();
                return;
            }
        }
        items.add(new LineItem(product, quantity));
//        refreshTotalPrice();
    }

    public void removeItem(LineItem item) {
        items.remove(item);
//        refreshTotalPrice();
    }

    public CheckoutDetails getCheckoutDetails() {
        return checkoutDetails;
    }

    public void setCheckoutDetails(CheckoutDetails checkoutDetails) {
        this.checkoutDetails = checkoutDetails;
    }

    public void setCheckout(CheckoutDetails checkoutDetails) {
        this.checkoutDetails = checkoutDetails;
    }

    public void refreshTotalPrice() {
        totalPrice = items.stream()
                .map(LineItem::getSubTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void refreshItemCount() {
        itemCount = items.stream()
            .map(LineItem::getQuantity)
            .reduce(0, Integer::sum);
    }

    private boolean isProductInItem(Product product, LineItem item) {
        return item.getProduct().getId() == product.getId();
    }

    public int getOrderID() {
        return this.orderID;
    }

    public int getUserID() {
        return this.userID;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public boolean containsAtLeastOneLineItem() {
        return items.size() > 0;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LineItem item : items) {
            sb.append(item);
            sb.append("\n");
        }
        return sb.toString();
    }
}
