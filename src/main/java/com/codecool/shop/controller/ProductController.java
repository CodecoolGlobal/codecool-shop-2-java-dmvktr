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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = ProductServiceFactory.get();

        Map<String, Object> templateVariables = new HashMap<>();
        templateVariables.put("order", productService.getOrderDao().getBy(1).orElse(null));
//        templateVariables.put("category", productService.getProductCategory(2));
        templateVariables.put("categories", productService.getProductCategoryDao().getAll());
        templateVariables.put("suppliers", productService.getSupplierDao().getAll());
        templateVariables.put("products", productService.getProductsForCategory(23423423));

        String htmlFilename = "product/index.html";
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }

}