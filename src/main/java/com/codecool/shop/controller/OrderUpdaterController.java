package com.codecool.shop.controller;

import com.codecool.shop.config.AppProperties;
import com.codecool.shop.controller.util.JsonReturner;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/update-order"})
public class OrderUpdaterController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(OrderUpdaterController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderDao orderDao = OrderDaoMem.getInstance();

        try {
            int userID = Integer.parseInt(req.getParameter("user_id"));
            int productID = Integer.parseInt(req.getParameter("product_id"));
            int quantity = Integer.parseInt(req.getParameter("quantity_diff"));
            orderDao.handleOrderUpdate(userID, productID, quantity);
        } catch (NumberFormatException e) {
            logger.warn("Unable to process product change (please check the query string)");
        }

        Order order = orderDao.getBy(1).orElse(null);
        JsonReturner.apply(resp, order);
    }
}