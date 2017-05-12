package jp.co.topgate.sugawara.web;

import org.junit.Ignore;
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
    @Ignore // TODO
    public void 正常にコネクションできたかを判断するテスト() {
        try {
            File htmlFile = new File("src/test/resources/index.html");
            InputStream inputStream = new FileInputStream(htmlFile);

            File pngFile = new File("src/test/resources/cream.png");
            OutputStream outputStream = new FileOutputStream(pngFile);

            HttpRequest httpRequest = new HttpRequest();
            // TODO httprequest.readRequest(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
