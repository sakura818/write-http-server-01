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
    public void HttpServerのテスト() throws IOException{

        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

        File filePath = new File(FILE_DIR, httpRequest.getFilePath());
        System.out.println("DummyHttpRequestのfilePathが正しくとれているか確認 " + filePath);
        // assertThat(src/main/resources/index.html, is(filePath));
        int statusCode = selectStatusCode(httpRequest, filePath);
        System.out.println("DummyHttpRequestのstatusCodeが正しくとれているか確認 " + statusCode);
        assertThat(200, is(statusCode));

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




