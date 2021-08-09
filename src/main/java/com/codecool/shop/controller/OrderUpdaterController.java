package com.codecool.shop.controller;

import com.codecool.shop.controller.util.JsonReturner;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ProductServiceFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/update-order"})
public class OrderUpdaterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = ProductServiceFactory.get();

        try {
            int userID = Integer.parseInt(req.getParameter("user_id"));
            int productID = Integer.parseInt(req.getParameter("product_id"));
            int quantity = Integer.parseInt(req.getParameter("quantity_diff"));
            productService.getOrderDao().handleOrderUpdate(userID, productID, quantity);
        } catch (NumberFormatException e) {
            System.out.println("Unable to process product change (please check the query string): " + e);
        }

        Order order = productService.getOrderDao().getBy(1).orElse(null);
        JsonReturner.apply(resp, order);
    }
}