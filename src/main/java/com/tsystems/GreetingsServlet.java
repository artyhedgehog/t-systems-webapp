package com.tsystems;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GreetingsServlet extends HttpServlet {

    private static final long serialVersionUID = 4281950606853738094L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String message;
        Object appName = req.getAttribute("app-name");
        if (appName instanceof String) {
            message = String.format("Greetings! My name is %s!", appName);
        } else {
            message = "Forgive me! I cannot greet you as I forgot my name.";
        }
        
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        out.println(message);
        out.println("</body>");
        out.println("</html>");
    }

    
}
