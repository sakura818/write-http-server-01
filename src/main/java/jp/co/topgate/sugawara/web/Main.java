package jp.co.topgate.sugawara.web;

/**
 * Main class
 * HttpServerのconnectメソッドを呼び出す
 *
 * @author sakura818
 */

public class Main {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.connect();
    }
}

