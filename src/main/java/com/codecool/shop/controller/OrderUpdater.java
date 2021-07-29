package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ProductServiceFactory;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = {"/update-order"})
public class OrderUpdater extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ProductServiceFactory.get();

        int userID = Integer.parseInt(req.getParameter("user_id"));
        int productID = Integer.parseInt(req.getParameter("product_id"));
        int quantity = Integer.parseInt(req.getParameter("quantity_diff"));
        System.out.println(userID + "-" + productID + "-" + quantity);

        productService.getOrderDao().handleOrderUpdate(userID, productID, quantity);

        Order order = productService.getOrderDao().getBy(1).orElse(null);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(order);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(jsonResponse);
        out.flush();
    }
}
