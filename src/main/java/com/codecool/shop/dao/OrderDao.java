package com.codecool.shop.dao;

import com.codecool.shop.model.*;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void handleOrderUpdate(int userID, int productID, int quantity);
    void handleOrderUnassignedToUserID(int orderID, int productID, int quantity);
    Order find(int orderID);
    void remove(int orderID);
    Order addOrder();
    Order addUserOrder(Integer userID);

    List<Order> getAll();
    Optional<Order> getBy(Integer userID);
    void setUsersOrderItemsToNull(int userID);

    void updateProductQuantityInOrder(Order order, Product product, int quantity);
    void mergeOrders(Order sessionOrderWithoutUserID, Order targetOrderWithUserID);

}