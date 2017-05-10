package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Main class
 * HttpServerを起動する
 *
 * @author sakura818
 */
public class Main {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.connection();
    }
}

