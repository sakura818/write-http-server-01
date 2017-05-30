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

@RunWith(Enclosed.class)
public class HttpRequestTest {
    public static class parseRequestUriメソッドのテスト {

        @Test
        public void requestLineからrequestUriを抜き出すテスト1() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseRequestUri("GET /index.html HTTP/1.1")), is("/index.html"));
        }

        @Test
        public void requestLineからrequestUriを抜き出すテスト2() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseRequestUri("GET http://localhost:8080/index.html HTTP/1.1")), is("http://localhost:8080/index.html"));
        }

        @Test
        public void requestLineからrequestUriを抜き出すテスト3() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseRequestUri("GET / HTTP/1.1")), is("/index.html"));
        }
    }

    public static class parseUriPathメソッドのテスト {

        @Test
        public void parseUriPathメソッドのrequestUriからUriPathを抜き出すテスト1() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseUriPath("/index.html")), is("/index.html"));
        }

        @Test
        public void parseUriPathメソッドのrequestUriからUriPathを抜き出すテスト2() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseUriPath("/index.html")), is("/index.html"));
        }

    }
}


