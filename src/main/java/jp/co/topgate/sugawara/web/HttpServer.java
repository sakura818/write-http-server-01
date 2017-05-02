package jp.co.topgate.sugawara.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/*
 * HttpServer class
 * connectionを管理する
 *  This Java source file was generated by the Gradle 'init' task.
 *
 * @author sakura818
 *
 */
public class HttpServer {

    private ServerSocket serverSocket = null;
    private String request;
    private String appendRequest;

    private Socket socket = null;
    private static final int PORT = 8080;
    private static final String FILE_DIR = "src/main/resources/";

    public String getRequest() {
        return this.request;
    }

    public String getAppendRequest() {
        return this.appendRequest;
    }


    /**
     * ソケット開閉やリクエストの入力、レスポンスの出力を行う
     */

    public void connection() throws IOException {
        try {

            System.out.println("request...");
            InputStream is = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();

            HttpRequest httpRequest = new HttpRequest();
            System.out.println("request incoming");
            System.out.println("---------------------------------------");


            HttpResponse httpResponse = new HttpResponse();
            httpResponse.generateHttpResponse(this.appendRequest);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    this.socket.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                System.out.println("正常にコネクションできないエラーが発生しました");

            }

        }

        /**
         * ファイルの存在有無を確認し、ステータスコードを返す。
         */

    public String fileExistsStatusLine() { // rename
        HttpRequest requestPath = new HttpRequest();
        try {
            String filepath = FILE_DIR + requestPath.requestUriDecodeAndPath();
            File file = new File(filepath);
            if (file.exists()) {
                String statusLine = "HTTP/1.1 200 OK";
                return statusLine;
            } else {
                String statusLine = "HTTP/1.1 404 Not Found";
                return statusLine;
            }
        } catch (IOException e) {
            //throw new IOException();
            String statusLine = "HTTP/1.1 500 Internal Server Error";
            return statusLine;
        } catch (URISyntaxException e) {
            //throw new URISyntaxException();
            String statusLine = "HTTP/1.1 400 Bad Request";
            return statusLine;
        }
    }
}




