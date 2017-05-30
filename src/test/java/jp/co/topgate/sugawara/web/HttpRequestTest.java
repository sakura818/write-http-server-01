package jp.co.topgate.sugawara.web;


import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpRequestTest Class
 * リクエストをパースするクラスをテストする
 *
 * @author sakura818
 */

public class HttpRequestTest {
    public static class parseRequestUriメソッドのテスト {

        @Test
        public void requestLineからrequestUriをgetするテスト1() throws IOException {
            InputStream inputStream1 = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest1 = new HttpRequest(inputStream1);
            assertThat((httpRequest1.parseRequestUri("GET /index.html HTTP/1.1")), is("/index.html"));
        }

        @Test
        public void requestLineからrequestUriをgetするテスト2() throws IOException {
            InputStream inputStream2 = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest2 = new HttpRequest(inputStream2);
            assertThat((httpRequest2.parseRequestUri("GET http://localhost:8080/index.html HTTP/1.1")), is("http://localhost:8080/index.html"));
        }
    }

    @Test
    public void parseUriPathメソッドのrequestUriからUriPathを抜き出すのテスト() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest = new HttpRequest(inputStream);
        assertThat((httpRequest.parseUriPath("http://localhost:8080/index.html")), is("index.html"));
    }

    @Test
    public void parseUriPathメソッドのrequestUriからUriPathを抜き出すのテスト2() throws IOException {
        InputStream inputStream2 = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest2 = new HttpRequest(inputStream2);
        assertThat((httpRequest2.parseUriPath("/index.html")), is("index.html"));
    }
}


