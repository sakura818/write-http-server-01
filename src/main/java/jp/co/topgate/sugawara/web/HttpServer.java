package jp.co.topgate.sugawara.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * HttpServer class
 * クライアントとサーバのデータの入出力を行う
 * TODO:データの入出力のメソッド呼び出し　
 *
 *
 * @author sakura818
 */

public class HttpServer {

    private Socket socket;
    final int PORT = 8080;

    /**
     * クライアントとサーバのデータの入出力を行う
     */

    public void connection() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("start up http server http://localhost:" + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("request incoming");

                InputStream inputStream = this.socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest();
                System.out.println(httpRequest.readHttpRequest(inputStream));

                OutputStream outputStream = this.socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse();

                inputStream.close();
                outputStream.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null) {
                    this.socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("正常にコネクションできないエラーが発生しました");
        }
    }
}




