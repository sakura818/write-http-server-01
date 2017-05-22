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

    private final String FILE_DIR = "src/main/resources/";

    @Test
    public void HttpServerのテスト() throws IOException {

        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

        File filePath = new File(FILE_DIR, httpRequest.getFilePath());
        System.out.println("DummyHttpRequestのfilePathが正しくとれているか確認 " + filePath);
        // assertThat("src/main/resources/index.html", is(filePath));
        HttpServer httpServer = new HttpServer();
        int statusCode = httpServer.selectStatusCode(httpRequest, filePath);
        System.out.println("DummyHttpRequestのstatusCodeが正しくとれているか確認 " + statusCode);
        assertThat(200, is(statusCode));

    }

}




