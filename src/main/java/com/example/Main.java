package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);  // Starts Jetty on port 8080

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(App.class, "/hello");  // Map the servlet to "/hello" path

        server.setHandler(handler);
        server.start();  // Start the Jetty server
        server.join();   // Keep the server running
    }
}
