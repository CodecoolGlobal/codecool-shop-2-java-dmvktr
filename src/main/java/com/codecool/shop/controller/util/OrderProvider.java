package com.codecool.shop.controller.util;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;

import javax.servlet.http.HttpSession;

public class OrderProvider {
    private static final OrderDao orderStore = OrderDaoMem.getInstance();

    public static Order get(HttpSession session){
        Integer userID = (Integer) session.getAttribute("user_id");
        Order order;
        if(userID != null){
            order = orderStore.getBy(userID).get();
        } else {
            order = (Order) session.getAttribute("cart");
        }
        return order;
    }
}
