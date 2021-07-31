package com.codecool.shop.controller;

import com.codecool.shop.controller.util.CheckoutDetailsFactory;
import com.codecool.shop.controller.util.EngineProcessor;
import com.codecool.shop.model.CheckoutDetails;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ProductServiceFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentProcessController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = ProductServiceFactory.get();

        CheckoutDetails checkoutDetails = CheckoutDetailsFactory.get(req);
        String paymentMethod = req.getParameter("payment-method");
        productService.getOrderDao().getBy(1).ifPresent(order -> order.setCheckoutDetails(checkoutDetails));
        String htmlFilename;
        if (paymentMethod.equals("paypal")) {
            htmlFilename = "product/paypal_payment.html";
        } else {
            htmlFilename = "product/credit_card_payment.html";
        }
        Map<String, Object> templateVariables = new HashMap<>();
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }

}
