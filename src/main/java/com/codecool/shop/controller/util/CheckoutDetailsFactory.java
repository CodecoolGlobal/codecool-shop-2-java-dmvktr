package com.codecool.shop.controller.util;

import com.codecool.shop.model.CheckoutDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDetailsFactory {

    public static CheckoutDetails get(HttpServletRequest req) {
        List<String> checkoutDetailsParams = new ArrayList<>();
        checkoutDetailsParams.add(req.getParameter("first_name"));
        checkoutDetailsParams.add(req.getParameter("last_name"));
        checkoutDetailsParams.add(req.getParameter("company"));
        checkoutDetailsParams.add(req.getParameter("email"));
        checkoutDetailsParams.add(req.getParameter("country"));
        checkoutDetailsParams.add(req.getParameter("street_address"));
        checkoutDetailsParams.add(req.getParameter("city"));
        checkoutDetailsParams.add(req.getParameter("zip_code"));
        checkoutDetailsParams.add(req.getParameter("phone_number"));
        checkoutDetailsParams.add(req.getParameter("comment"));

        return new CheckoutDetails(checkoutDetailsParams);
    }

}
