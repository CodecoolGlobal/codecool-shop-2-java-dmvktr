package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.CheckoutDetails;
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
import java.util.Objects;
import java.util.Optional;


@WebServlet(urlPatterns = {"/order_confirmation"})
public class PaymentConfirmation extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ProductServiceFactory.get();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Optional<Order> order = OrderDaoMem.getInstance().getBy(Integer.parseInt(req.getParameter("user_id")));
        /*context.setVariable("first_name",order.get().getCheckoutDetails().getFirstName());
        context.setVariable("last_name",order.get().getCheckoutDetails().getLastName());
        context.setVariable("company",order.get().getCheckoutDetails().getCompany());
        context.setVariable("email",order.get().getCheckoutDetails().getEmail());
        context.setVariable("country",order.get().getCheckoutDetails().getCountry());
        context.setVariable("street_address",order.get().getCheckoutDetails().getStreetAddress());
        context.setVariable("city",order.get().getCheckoutDetails().getCity());
        context.setVariable("zip_code",order.get().getCheckoutDetails().getZipCode());
        context.setVariable("phone_number",order.get().getCheckoutDetails().getPhoneNumber());
        context.setVariable("comment",order.get().getCheckoutDetails().getComment());*/


        context.setVariable("order", order.orElse(null));
        engine.process("product/confirmation.html", context, resp.getWriter());
    }

}
