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
    //TODO:このテストの書き方
    public void HttpServerのテスト() {
    }

    @Test
    public void HttpRequestに応じて適切なステータスコードを返すテスト() {
        assertThat(200, is(httpServer.getStatusCode(new File("src/test/resources/index.html"))));
        assertThat(404, is(httpServer.getStatusCode(new File("src/test/resources/hoge.html"))));
    }

}




