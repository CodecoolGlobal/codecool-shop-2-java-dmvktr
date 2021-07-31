package com.codecool.shop.controller;

import com.codecool.shop.controller.util.EngineProcessor;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.*;
import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ProductServiceFactory.get();

        Map<String, Object> templateVariables = new HashMap<>();
        templateVariables.put("order", productService.getOrderDao().getBy(1).orElse(null));
        templateVariables.put("category", productService.getProductCategory(1));
        templateVariables.put("categories", productService.getProductCategoryDao().getAll());
        templateVariables.put("suppliers", productService.getSupplierDao().getAll());
        templateVariables.put("products", productService.getProductsForCategory(1));

        String htmlFilename = "product/index.html";
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }

}
