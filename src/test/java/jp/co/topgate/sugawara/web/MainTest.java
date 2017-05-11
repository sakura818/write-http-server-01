package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * MainTest Class
 * testフォルダ全体のテストを行う
 *
 * @author sakura818
 */
public class MainTest {

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.connection();
    }
}

