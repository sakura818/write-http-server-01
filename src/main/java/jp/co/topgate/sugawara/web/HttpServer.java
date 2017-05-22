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
    private ServerSocket serverSocket;
    private final int PORT = 8080;
    private final String FILE_DIR = "src/main/resources/";

    /**
     * クライアントとサーバのデータの入出力を行う
     * TODO:try-catchの範囲が広いのでなおす
     */

    public void connect() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("start up http server http://localhost:" + PORT);
            while (true) {
                socket = serverSocket.accept();
                System.out.println("http request incoming");
                System.out.println("http request...");

                InputStream inputStream = this.socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest(inputStream);
                //httpRequest.printHttpRequest();

                File file = new File(FILE_DIR, httpRequest.getFilePath());
                int statusCode = getStatusCode(httpRequest, file);

                OutputStream outputStream = this.socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse();

                httpResponse.writeToOutputStream(outputStream, file, statusCode);

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
                if (serverSocket != null) {
                    this.serverSocket.close();
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
     * @param file    ex:index.html
     * @return statusCode ex:200
     * TODO:selectStatusCodeメソッドの位置が微妙かもしれない 新たにクラスをつくる?
     */

    public int getStatusCode(HttpRequest httpRequest, File file) {
        if (httpRequest.getMethod() == null) {
            return 400;
        }
        if (!file.exists()) {
            return 404;
        }
        return 200;
    }

}





