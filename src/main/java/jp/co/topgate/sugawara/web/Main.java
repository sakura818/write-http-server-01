package jp.co.topgate.sugawara.web;

import java.io.IOException;

/**
 * Main class
 * HttpServer classを呼び出す
 * @author sakura818
 */
public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        server.connection();
    }

}
