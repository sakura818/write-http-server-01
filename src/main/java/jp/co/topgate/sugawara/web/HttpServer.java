package jp.co.topgate.sugawara.web;


import java.io.*;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;


/*
 * HttpServer class
 * connectionを管理する
 *
 * @author sakura818
 *
 */
public class HttpServer {

    private Socket socket;
    int PORT = 8080;
    // private final String HOSTNAME = "localhost";
    private static final String FILE_DIR = "src/main/resources/";

    public HttpServer(Socket socket, int PORT) {
        this.PORT = PORT;
        this.socket = socket;
    }

    /**
     * 入出力の管理を行う
     */

    public void connection() {
        try {
            System.out.println("request...");
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();

            HttpRequest httpRequest = new HttpRequest();
            httpRequest.readRequest(inputStream);
            System.out.println("request incoming");
            System.out.println("---------------------------------------");

            File file = new File(FILE_DIR, httpRequest.getFilePath());
            HttpHandler httpHandler = new HttpHandler();

            int statusCode = selectStatusCode(httpRequest, file);

            switch (httpRequest.getMethod()) {
                case "GET":
                    httpHandler.handlerGet(statusCode, file, outputStream);
                    break;
                case "HEAD":
                    httpHandler.handlerError(statusCode, outputStream);
                    break;
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
     * リクエストに対応して適切なステータスコードを返す
     */

    private int selectStatusCode(HttpRequest httpRequest, File file) {
        if (httpRequest.getMethod() == null) {
            return 400;
        }
        if (!file.exists()) {
            return 404;
        }
        return 200;
    }
}




