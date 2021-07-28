package com.codecool.shop.dao;

import com.codecool.shop.model.*;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Order addOrder(int userID);
    Order find(int orderID);
    void remove(int orderID);

    List<Order> getAll();
    Optional<Order> getBy(int userID);
    //List<Product> getBy();

    void addProductToOrder(Order order, Product product, int quantity);


}
