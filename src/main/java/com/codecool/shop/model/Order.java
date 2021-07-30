package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Order keeps track of the user id, all line items with products and quantities.
 * Checkout details are also stored here in later stages of the Checkout process
 */
public class Order {

    private int orderID;
    private final int userID;
    private List<LineItem> items;
    private BigDecimal totalPrice;
    private int itemCount;
    private CheckoutDetails checkoutDetails;


    public Order(int userID) {
        this.userID = userID;
        items = new ArrayList<>();
    }

    private void addProduct(Product product, int quantity) {
        for (LineItem item : items) {
            if (isProductInItem(product, item)) {
                item.updateQuantity(quantity);
                return;
            }
        }
        items.add(new LineItem(product, quantity));
    }

    public void removeItem(LineItem item) {
        items.remove(item);
    }

    public void setItemsToNull() {
        items = new ArrayList<>();
        refreshTotalPrice();
        refreshItemCount();
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
