package jp.co.topgate.sugawara.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * HttpServer class
 * クライアントとサーバのデータの入出力を行う
 *
 * @author sakura818
 */

public class HttpServer {

    private Socket socket;
    private final int PORT = 8080;

    /**
     * クライアントとサーバのデータの入出力を行う
     */

    public void connection() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("start up http server http://localhost:" + PORT);
            while (true) {
                socket = serverSocket.accept();
                System.out.println("request incoming");

                InputStream inputStream = this.socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest(inputStream);

                System.out.println("request show");
                httpRequest.sysoutHttpRequest();

                File filePath = new File(httpRequest.getFilePath());
                int statusCode = selectStatusCode(httpRequest, filePath);

                OutputStream outputStream = this.socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse();
                httpResponse.writeResponseOutputStream(outputStream, filePath, statusCode);

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

    /**
     * HttpRequestに応じて適切なステータスコードを返す
     *
     * @param httpRequest
     * @param filePath    ex:index.html
     * @return statusCode ex:200
     */

    public int selectStatusCode(HttpRequest httpRequest, File filePath) {
        if (httpRequest.getMethod() == null) {
            return 400;
        }
        if (!filePath.exists()) {
            return 404;
        }
        return 200;
    }

}




