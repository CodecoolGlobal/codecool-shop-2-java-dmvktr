package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.util.EngineProcessor;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ProductServiceFactory.get();

        Map<String, Object> templateVariables = new HashMap<>();
        // TODO remove hardcoded order #1 during 2nd sprint
        templateVariables.put("order", productService.getOrderDao().getBy(1).orElse(null));

        String htmlFilename = "product/checkout.html";
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }
}