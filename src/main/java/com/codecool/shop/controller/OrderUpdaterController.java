package com.codecool.shop.controller;

import com.codecool.shop.controller.util.JsonReturner;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/update-order"})
public class OrderUpdaterController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(OrderUpdaterController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderDao orderDao = OrderDaoMem.getInstance();
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("cart");
        int quantityDiff = 0;
        int productID = 0;

        try {
            productID = Integer.parseInt(req.getParameter("product_id"));
            quantityDiff = Integer.parseInt(req.getParameter("quantity_diff"));
        } catch (NumberFormatException e) {
            logger.warn("Unable to process product change (please check the query string)");
        }
        if(isUserLoggedIn(req)){
            Integer userID = (Integer) session.getAttribute("user_id");
            orderDao.handleOrderUpdate(userID, productID, quantityDiff);
            session.setAttribute("cart", order);
        } else {
            orderDao.handleOrderUnassignedToUserID(order.getOrderID(), productID, quantityDiff);
        }
        session.setAttribute("cart", order);
        JsonReturner.apply(resp, order);
    }

    private boolean isUserLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute("user_id") != null;
    }
}