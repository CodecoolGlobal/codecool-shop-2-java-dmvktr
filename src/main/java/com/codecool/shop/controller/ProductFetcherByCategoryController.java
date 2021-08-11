package com.codecool.shop.controller;

import com.codecool.shop.controller.util.JsonReturner;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/getProductsByCategory"})
public class ProductFetcherByCategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = ProductServiceStore.get();

        int categoryId = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.getProductsForCategory(categoryId);
        JsonReturner.apply(resp, products);
    }

}
