package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public void doGet(HttpExchange exchange) throws IOException {
        String response = "Hello world";

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static void main(String[] args) throws IOException {
        App servlet = new App();

        HttpServer server = HttpServer.create(new InetSocketAddress(8085), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                servlet.doGet(exchange);
            }
        });

        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port 8085...");
    }
}