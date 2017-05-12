package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;
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
    @Test
    public void HttpServerのテスト() {
        try {

            System.out.println("request show");
            File exampleRequestFile = new File("src/test/resources/exampleRequest.txt");
            InputStream inputStream = new FileInputStream(exampleRequestFile);
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.showHttpRequest(inputStream);

            File responseFile = new File("src/test/resources/cream.png");
            OutputStream outputStream = new FileOutputStream(responseFile);
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.writeResponseOutputStream(outputStream);

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




