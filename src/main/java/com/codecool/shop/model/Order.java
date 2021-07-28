package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderID;
    private int userID;
    private List<LineItem> items = new ArrayList<>();

    public Order(int userID) {
        this.userID = userID;
    }

    private void addProduct(Product product, int quantity) {
        for (LineItem item : items) {
            if (isProductInItem(product, item)) {
                item.setQuantity(quantity);
                return;
            }
        }
        items.add(new LineItem(product, quantity));
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

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
