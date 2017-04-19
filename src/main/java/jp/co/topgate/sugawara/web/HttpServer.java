package jp.co.topgate.sugawara.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class HttpServer {

    private static final int PORT = 8080;//privateは自分自身のクラスのみアクセスを許可する　staticは静的フィールド（フィールド変数の実体がクラスに準備される）
    ServerSocket serverSocket = null;
    Socket socket = null;

    public void connection() throws IOException {
        System.out.println("start up http server http://localhost:8080");
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {

                this.socket = serverSocket.accept();
                System.out.println("request incoming");

                HttpRequest httpRequest;
                //request
                InputStream inputStream = this.socket.getInputStream();//?
                try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {


                    List<String> lines = new ArrayList<>();
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        lines.add(line);
                    }
                    httpRequest = new HttpRequest(lines);
                }

                //response
                OutputStream outputStream = this.socket.getOutputStream();//?
                HttpResponse httpResponse = new HttpResponse();



            }
        } catch (IOException e) {
            System.out.println("正常にコネクションできないエラーが発生しました");
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("ソケットを閉じれないエラーが発生しました");
            }

        }
    }
}

