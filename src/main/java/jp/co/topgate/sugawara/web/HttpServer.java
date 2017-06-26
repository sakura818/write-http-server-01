package jp.co.topgate.sugawara.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;


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
                StaticHttpResponse staticHttpResponse = null;
                BoardDynamicHttpResponseHandler boardDynamicHttpResponseHandler = null;
                String staticOrDynamic = "";

                try {
                    httpRequest = new HttpRequest(inputStream);
                    statusCode = httpRequest.getStatusCode();
                    if (statusCode == OK) {
                        file = new File(this.FILEPATH_DIR, httpRequest.getUriPath());
                        if (file.isDirectory()) {
                            file = new File(this.FILEPATH_DIR, httpRequest.getUriPath() + "/index.html");
                        }
                        Path path = file.toPath();

                        if (httpRequest.getUriPath().startsWith("/program/board/")) {
                            staticOrDynamic = "dynamic";//TODO: 命名、2値ならboolean値のほうがいいのかもしれない

                        } else {
                            staticOrDynamic = "static";
                            if (!path.normalize().startsWith(FILEPATH_DIR)) {
                                statusCode = NOT_FOUND;
                            }
                            if (!file.exists()) {
                                statusCode = NOT_FOUND;
                            }
                        }
                    }
                    if (statusCode != OK) {
                        switch (statusCode) {
                            case NOT_FOUND:
                                file = new File(this.FILEPATH_DIR, "NotFound.html");
                                break;
                            case BAD_REQUEST:
                                file = new File(this.FILEPATH_DIR, "BadRequest.html");
                                break;
                            case NOT_IMPLEMENTED:
                                file = new File(this.FILEPATH_DIR, "NotImplemented.html");
                                break;
                            case HTTP_VERSION_NOT_SUPPORTED:
                                file = new File(this.FILEPATH_DIR, "HttpVersionNotSupported.html");
                                break;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                OutputStream outputStream = this.socket.getOutputStream();

                try {
                    if (staticOrDynamic.equals("static")) {
                        staticHttpResponse = new StaticHttpResponse(file, statusCode);
                        staticHttpResponse.writeToOutputStream(outputStream);
                    } else if (staticOrDynamic.equals("dynamic")) {
                        new BoardDynamicHttpResponseHandler(file, statusCode, httpRequest, outputStream, inputStream);//TODO:staticではwriteToOutPutStreamよびだしてるのにdynamicではよびだしていなくて不均衡 どちらかに統一すべきかもしれない
                    }
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
                if ((this.socket != null) || (this.serverSocket != null)) {//TODO:ここの書き方あまり良くない socketのほうがクライアント側だから先に処理した方がいい気がする
                    this.socket.close();
                    this.serverSocket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("正常にコネクションできないエラーが発生しました");
        }
    }

}





