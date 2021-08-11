package com.codecool.shop.controller;

import com.codecool.shop.controller.util.EngineProcessor;
import com.codecool.shop.service.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> templateVariables = new HashMap<>();
        String htmlFilename = "user-related/login.html";
        EngineProcessor.apply(req, resp, templateVariables, htmlFilename);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String userInSession = req.getParameter("user-name");
        session.setAttribute("username", userInSession);
        resp.sendRedirect(req.getContextPath() + "/");
    }

}
