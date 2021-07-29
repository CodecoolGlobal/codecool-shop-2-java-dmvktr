package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.ProductServiceFactory;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = {"/getProductsBySuppliers"})
public class ProductFetcherBySuppliers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ProductServiceFactory.get();

        Enumeration<String> supplierIDs = req.getParameterNames();
        List<Product> products;
        if (!supplierIDs.hasMoreElements()) {
            products = null;
        } else {
            List<Integer> ids = new ArrayList<>();
            while (supplierIDs.hasMoreElements()) {
                String id = supplierIDs.nextElement();
                ids.add(Integer.valueOf(id));
            }
            products = productService.getProductsForSuppliers(ids);
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(products);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(jsonResponse);
        out.flush();
    }

}
