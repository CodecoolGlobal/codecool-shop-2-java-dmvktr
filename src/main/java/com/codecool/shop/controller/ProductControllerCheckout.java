package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ProductServiceFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/checkout"})
public class ProductControllerCheckout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ProductServiceFactory.get();

        // todo get user_id
        Order order = productService.getOrderDao().getBy(1).orElse(null);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("user_id", req.getParameter("user_id"));
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("categories", productService.getProductCategoryDao().getAll());
        context.setVariable("suppliers", productService.getSupplierDao().getAll());
        context.setVariable("products", productService.getProductsForCategory(1));
        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String company = req.getParameter("company");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String streetAddress = req.getParameter("street_address");
        String city = req.getParameter("city");
        String zipCode = req.getParameter("zip_code");
        String phoneNumber = req.getParameter("phone_number");
        String comment = req.getParameter("comment");
        System.out.println(firstName + email + country);
        Optional<Order> order = OrderDaoMem.getInstance().getBy(Integer.parseInt(req.getParameter("user_id")));
    }

}
