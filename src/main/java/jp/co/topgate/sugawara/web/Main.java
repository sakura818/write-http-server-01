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

    // private ServerSocket serverSocket = null;
    // private Socket socket = null;


    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("start up http server http://localhost:" + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                HttpServer server = new HttpServer();
                server.connection();
            }
        } catch (IOException e) {
            System.out.println("正常にコネクションできないエラーが発生しました");
            e.printStackTrace();

        }
    }
}

