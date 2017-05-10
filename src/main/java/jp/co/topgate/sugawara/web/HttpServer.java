package jp.co.topgate.sugawara.web;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;


/*
 * HttpRequestの処理をする
 *
 * @author sakura818
 *
 */

public class HttpServer {

    private ServerSocket serverSocket = null;
    private Socket socket;
    int PORT = 8080;

    /**
     * クライアントとサーバのデータの入出力をを行う
     */

    public void connection() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("start up http server http://localhost:" + PORT);
            while (true) {
                this.socket = serverSocket.accept();
                System.out.println("request incoming");
                System.out.println("---------------------------------------");

                InputStream inputStream = this.socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest();

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




