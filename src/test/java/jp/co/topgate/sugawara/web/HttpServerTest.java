package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpServerTest Class
 * クライアントとサーバのデータの入出力がきちんとできているかのクラス
 *
 * @author sakura818
 */
public class HttpServerTest {
    private final String FILEPATH_DIR = "src/test/resources/";
    HttpServer httpServer = new HttpServer();

    @Test
    public void UriPathとFILEPATH_DIRからfilePathが生成できているかのテスト() {
        File filePath = new File(this.FILEPATH_DIR, "index.html");
        System.out.println(filePath);
        assertThat(filePath, is(new File("src/test/resources/index.html")));
    }

    @Test
    public void HttpRequestに応じて適切なステータスコードを返すテスト() {
        assertThat(httpServer.getStatusCode(new File("src/test/resources/index.html")), is(200));
        assertThat(httpServer.getStatusCode(new File("src/test/resources/hoge.html")), is(404));
    }

}




