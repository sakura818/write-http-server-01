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
    private final int OK = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_FOUND = 404;
    private final int NOT_IMPLEMENTED = 501;
    private final int HTTP_VERSION_NOT_SUPPORTED = 505;


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
                    statusCode = catchStatusCode(file);
                    if (statusCode == OK) {
                        file = new File(this.FILEPATH_DIR, httpRequest.getUriPath());
                        if (statusCode == NOT_FOUND) {
                            file = new File(this.FILEPATH_DIR, "NotFound.html");
                        }
                    }
                } catch (HttpVersionNotSupported) {
                    statusCode = HTTP_VERSION_NOT_SUPPORTED;
                    file = new File(this.FILEPATH_DIR, "HttpVersionNotSupported.html");
                } catch (NotImplemented) {
                    statusCode = NOT_IMPLEMENTED;
                    file = new File(this.FILEPATH_DIR, "NotImplemented.html");
                } catch (BadRequestException) {
                    statusCode = BAD_REQUEST;
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
                if ((this.socket != null) || (this.serverSocket != null)) {
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
            return NOT_FOUND;
        }
        return OK;
    }

}





