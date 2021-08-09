package com.codecool.shop.controller.util;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonReturner {

    public static <T> void apply (HttpServletResponse resp, T dataToSend ) throws IOException {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(dataToSend);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(jsonResponse);
        out.flush();
    }
}
