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

                InputStream inputStream = this.socket.getInputStream();
                HttpRequest httpRequest;

                File file = null;
                int statusCode = 0;
                try {
                    httpRequest = new HttpRequest(inputStream);
                    if (httpRequest.getStatusCode() == 500) {
                        statusCode = 500;
                        file = new File(this.FILEPATH_DIR, "InternalServerError.html");
                    }
                    if (httpRequest.getStatusCode() == 200) {
                        file = new File(this.FILEPATH_DIR, httpRequest.getUriPath());
                        statusCode = catchStatusCode(file);
                        if (statusCode == 404) {
                            file = new File(this.FILEPATH_DIR, "NotFound.html");
                        }
                    }
                } catch (IOException e) {
                    statusCode = 400;
                    file = new File(this.FILEPATH_DIR, "BadRequest.html");
                }

                OutputStream outputStream = this.socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse(file, statusCode);
                try {
                    httpResponse.writeToOutputStream(outputStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                inputStream.close();
                outputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if ((this.socket != null) && (this.serverSocket != null)) {
                    this.socket.close();
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

    int catchStatusCode(File file) {
        if (!file.exists()) {
            return 404;
        }
        return 200;
    }

}





