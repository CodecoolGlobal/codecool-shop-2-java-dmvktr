package com.codecool.shop.controller;

import com.codecool.shop.controller.util.EngineProcessor;
import com.codecool.shop.service.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = ProductServiceFactory.getProductService();

        Map<String, Object> templateVariables = new HashMap<>();
        // TODO remove hardcoded order #1 during 2nd sprint
        templateVariables.put("order", productService.getOrderDao().getBy(1).orElse(null));

        String htmlFilename = "product/cart.html";
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }

}