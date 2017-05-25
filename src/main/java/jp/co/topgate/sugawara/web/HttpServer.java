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
    private final String FILEPATH_DIR = "src/main/resources/";

    /**
     * クライアントとサーバのデータの入出力を行う
     */

    public void connect() {

        try {
            this.serverSocket = new ServerSocket(this.PORT);
            System.out.println("start up http server http://localhost:" + this.PORT);
            while (true) {
                this.socket = this.serverSocket.accept();
                System.out.println("http request incoming");
                System.out.println("http request line...");

                InputStream inputStream = this.socket.getInputStream();

                HttpRequest httpRequest;

                File file = null;
                int statusCode;
                try {
                    httpRequest = new HttpRequest(inputStream);
                    file = new File(this.FILEPATH_DIR, httpRequest.getUriPath());
                    statusCode = getStatusCode(file);
                    //if (statusCode == 404){file ="src/main/resources/statusCode404.html";}
                } catch (Exception e) {
                    statusCode = 400;
                    //file = "src/main/resources/statusCode400.html";
                }

                OutputStream outputStream = this.socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse(outputStream, file, statusCode);

                httpResponse.writeToOutputStream(outputStream, file, statusCode);

                inputStream.close();
                outputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
                if (this.serverSocket != null) {
                    this.serverSocket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("正常にコネクションできないエラーが発生しました");
        }
    }

    /**
     * 適切なステータスコードを返す
     *
     * @param file ex:index.html
     * @return statusCode ex:200
     */

    int getStatusCode(File file) {
        if (!file.exists()) {
            return 404;
        }
        return 200;
    }

}





