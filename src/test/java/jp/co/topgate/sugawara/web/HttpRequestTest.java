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

    public static class judgeStatusCodeメソッドのテスト {

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
            assertThat((httpRequest.judgeStatusCode("POST /index.html HTTP/1.1")), is(501));
        }

        @Test
        public void requestLineからstatusCode505をjudgeするテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/2".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.judgeStatusCode("GET /index.html HTTP/2")), is(505));
        }

        @Test
        public void requestLineからstatusCode400をjudgeするテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("hoge hoge hoge hoge".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.judgeStatusCode("hoge hoge hoge hoge")), is(400));
        }
    }

    public static class parseRequestUriメソッドのテスト {

        @Test
        public void requestLineから相対パスのrequestUriを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseRequestUri("GET /index.html HTTP/1.1")), is("/index.html"));
        }

        @Test
        public void requestLineから絶対パスのrequestUriを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseRequestUri("GET http://localhost:8080/index.html HTTP/1.1")), is("http://localhost:8080/index.html"));
        }

        @Test
        public void requestLineからスラッシュだけのrequestUriを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseRequestUri("GET / HTTP/1.1")), is("/index.html"));
        }
    }

    public static class parseUriPathメソッドのテスト {

        @Test
        public void 相対パスのrequestUriからUriPathを抜き出すテスト1() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseUriPath("/index.html")), is("/index.html"));
        }

        @Test
        public void 絶対パスのrequestUriからUriPathを抜き出すテスト2() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.parseUriPath("/index.html")), is("/index.html"));
        }

    }
}


