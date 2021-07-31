package com.codecool.shop.controller.util;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class EngineProcessor {

    public static void apply(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> templateVariables, String htmlFilename) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        for (Map.Entry<String, Object> entry : templateVariables.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        engine.process(htmlFilename, context, resp.getWriter());
    }
}
