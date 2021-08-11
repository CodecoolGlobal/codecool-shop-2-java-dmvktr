package com.codecool.shop.controller;

import com.codecool.shop.controller.util.EngineProcessor;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Integer userID = (Integer) session.getAttribute("user_id");
        ProductService productService = ProductServiceStore.get();
        OrderDao orderDao = OrderDaoMem.getInstance();
        Order order = (Order) session.getAttribute("cart");

        Map<String, Object> templateVariables = new HashMap<>();

        if(userID == null && order == null) templateVariables.put("order", orderDao.addOrder());
        else if(userID == null) templateVariables.put("order", order);
        else templateVariables.put("order", orderDao.getBy(userID));
        templateVariables.put("categories", productService.getProductCategoryDao().getAll());
        templateVariables.put("suppliers", productService.getSupplierDao().getAll());
        templateVariables.put("products", productService.getProductsForCategory(1));

        String htmlFilename = "product/index.html";
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }

}