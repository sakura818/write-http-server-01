package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpServerTest Class
 * クライアントとサーバのデータの入出力がきちんとできているかのクラス
 *
 * @author sakura818
 */
public class HttpServerTest {

    private Socket socket;
    private final int PORT = 8080;
    private final String FILE_DIR = "src/main/resources/";

    @Test
    public void HttpServerのテスト() {
        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("start up http server http://localhost:" + PORT);
            while (true) {
                socket = serverSocket.accept();
                System.out.println("request incoming");

                System.out.println("request...");
                InputStream inputStream = this.socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest(inputStream);

                File filePath = new File(FILE_DIR, httpRequest.getFilePath());
                System.out.println("filePathが正しくとれているか確認" + filePath);
                int statusCode = selectStatusCode(httpRequest, filePath);
                System.out.println("statusCodeが正しくとれているか確認" + statusCode);

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




