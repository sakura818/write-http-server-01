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
            Method method = new Method();

            int currentStatusCode = selectStatusCode(httpRequest, file);
            /*
            switch (httpRequest.getMethod()) {
                case "GET":
                    method.methodGET(currentStatusCode, file, outputStream);
                    break;
                case "HEAD":
                    method.methodHEAD(currentStatusCode, outputStream);
                    break;
            }
            */
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




