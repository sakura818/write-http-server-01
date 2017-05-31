package jp.co.topgate.sugawara.web;


import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.Arrays;

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


    public static class judgeStatusCodeメソッドのテストです {

        private String requestLine;
        private int statusCode;

        // これコンストラクタ

        public judgeStatusCodeメソッドのテストです(String requestLine, int statusCode) {
            this.requestLine = requestLine;
            this.statusCode = statusCode;
        }

        @Parameterized.Parameters(name = "{0} converts to {1}.")
        public Iterable<Object[]> getParameters() {
            return Arrays.asList(new Object[][]{
                    {"GET /index.html HTTP/1.1", 200},
                    {"POST /index.html HTTP/1.1", 501},
                    {"GET /index.html HTTP/2", 505},
                    {"hoge hoge hoge hoge", 400}
            });
        }

        @Test
        public void requestLineからstatusCodeを判別するテスト()throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.judgeStatusCode(requestLine, is(statusCode)));
        }

    }

    public static class judgeStatusCodeメソッドのテスト {

        private final int OK = 200;
        private final int BAD_REQUEST = 400;
        private final int NOT_IMPLEMENTED = 501;
        private final int HTTP_VERSION_NOT_SUPPORTED = 505;


        @Test
        public void requestLineからstatusCode200をjudgeするテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getStatusCode(), is(200));
        }

        @Test
        public void requestLineからstatusCode501をjudgeするテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("POST /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.judgeStatusCode("POST /index.html HTTP/1.1")), is(NOT_IMPLEMENTED));
        }

        @Test
        public void requestLineからstatusCode505をjudgeするテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/2".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.judgeStatusCode("GET /index.html HTTP/2")), is(HTTP_VERSION_NOT_SUPPORTED));
        }

        @Test
        public void requestLineからstatusCode400をjudgeするテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("hoge hoge hoge hoge".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.judgeStatusCode("hoge hoge hoge hoge")), is(BAD_REQUEST));
        }
    }

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
        public void requestUriからUriPathを抜き出すテスト1() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseUriPath("/index.html")), is("/index.html"));
        }

        @Test
        public void requestUriからUriPathを抜き出すテスト2() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseUriPath("/index.html")), is("/index.html"));
        }

    }
}


