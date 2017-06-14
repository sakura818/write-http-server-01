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
        public void GETリクエストにはステータスコード200を返すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getStatusCode(), is(200));
        }

        @Test
        public void POSTリクエストにはステータスコード501を返すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("CONNECT /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.getStatusCode()), is(501));
        }

        @Test
        public void HttpVersion2のリクエストにはステータスコード505を返すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/2".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.getStatusCode()), is(505));
        }

        @Test
        public void 不正なシンタックスのリクエストにはステータスコード400を返すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("hoge hoge hoge hoge".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat((httpRequest.getStatusCode()), is(400));
        }
    }

    public static class parseRequestUriメソッドのテスト {

        @Test
        public void リクエストの1行目から相対パスのrequestUriを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getRequestUri(), is("/index.html"));
        }

        @Test
        public void リクエストの1行目から絶対パスのrequestUriを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getRequestUri(), is("http://localhost:8080/index.html"));
        }

        @Test
        public void リクエストの1行目からスラッシュだけのrequestUriを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getRequestUri(), is("/index.html"));
        }

        @Test
        public void リクエストの1行目から階層構造に応じたファイルのrequestUriを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /dummyDirectory/sample.txt HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getRequestUri(), is("/dummyDirectory/sample.txt"));
        }

    }

    public static class parseUriPathメソッドのテスト {

        @Test
        public void 相対パスのrequestUriからuriPathを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getUriPath(), is("/index.html"));
        }

        @Test
        public void 絶対パスのrequestUriからuriPathを抜き出すテスト() throws IOException {
            InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
            HttpRequest httpRequest = new HttpRequest(inputStream);
            assertThat(httpRequest.getUriPath(), is("/index.html"));

        }
    }
}


