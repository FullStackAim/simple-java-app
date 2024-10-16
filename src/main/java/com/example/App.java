package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/hello")
public class App extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String environment = System.getenv("ENVIRONMENT");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>THIS IS THE DEV ENVIRONEMENT</h1>");
        out.println("<p>Running in vvvvvvvv " + environment + " environment.</p>");
        out.println("</body></html>");
    }
}
