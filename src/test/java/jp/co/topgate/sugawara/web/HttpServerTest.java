package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/04/20.
 */
public class HttpServerTest {
    @Test
    public void 正常にコネクションできたかを判別するHttpServerのテスト() {
        try {
            File htmlFile = new File("src/test/resources/hello.html");
            InputStream inputStream = new FileInputStream(htmlFile);

            File pngFile = new File("src/test/resources/cream.png");
            OutputStream outputStream = new FileOutputStream(pngFile);

            HttpRequest request = new HttpRequest();
            request.readRequest(inputStream, "localhost:8080");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
