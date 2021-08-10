package com.codecool.shop.controller;

import com.codecool.shop.controller.util.JsonReturner;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/getProductsBySuppliers"})
public class ProductFetcherBySuppliersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = ProductServiceFactory.get();

        Enumeration<String> supplierIDs = req.getParameterNames();
        List<Product> products = null;
        if (supplierIDs.hasMoreElements()) {
            List<Integer> ids = new ArrayList<>();
            while (supplierIDs.hasMoreElements()) {
                String id = supplierIDs.nextElement();
                ids.add(Integer.valueOf(id));
            }
            products = productService.getProductsForSuppliers(ids);
        }

        JsonReturner.apply(resp, products);
    }

}