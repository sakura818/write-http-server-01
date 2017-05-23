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
    HttpServer httpServer = new HttpServer();

    @Test   
    public void connectメソッドのテスト() throws IOException {
        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);

        File DummyHttpResponse = new File("src/test/resources/DummyHttpResponse.txt");
        OutputStream outputStream = new FileOutputStream(DummyHttpResponse);

        inputStream.close();
        outputStream.close();
    }

    @Test
    public void 適切なstatusCodeを選択できているかのテスト() throws IOException {
        assertThat(200, is(httpServer.getStatusCode(new File("src/test/resources/DummyHttpRequest.txt"))));
        assertThat(404, is(httpServer.getStatusCode(new File("hoge.txt"))));
    }

}




