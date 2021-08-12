package com.codecool.shop.controller;

import com.codecool.shop.controller.util.EngineProcessor;
import com.codecool.shop.controller.util.OrderProvider;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> templateVariables = new HashMap<>();
        Order order = OrderProvider.get(req.getSession());
        templateVariables.put("order", order);

        String htmlFilename = "product/cart.html";
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }

}