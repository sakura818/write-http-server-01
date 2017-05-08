package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by haruka.sugawara on 2017/05/08.
 */
public class MainTest {
    final static int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("start up http server http://localhost:" + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                HttpServer httpServer = new HttpServer(socket, PORT);
                httpServer.connection();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

