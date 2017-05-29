package jp.co.topgate.sugawara.web;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpRequestTest Class
 * リクエストを分解するのをテストするクラス
 *
 * @author sakura818
 */

public class HttpRequestTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void requestLineからrequestUriをgetするテスト1() throws IOException {
        InputStream inputStream1 = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest1 = new HttpRequest(inputStream1);
        assertThat((httpRequest1.parseRequestUri("GET /index.html HTTP/1.1")), is("/index.html"));

        /*
        assertThat("/index.html", is(httpRequest2.getRequestUri("hoge /index.html HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("GET /index.html hoge")));
        assertThat("/index.html", is(httpRequest.getRequestUri("GET /index.html HTTP/1.1 HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("")));
        assertThat("/index.html", is(httpRequest.getRequestUri("GET HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("/index.html")));
        assertThat("/index.html", is(httpRequest.getRequestUri("/index.html")));
        */
    }

    @Test
    public void requestLineからrequestUriをgetするテスト2() throws IOException {
        InputStream inputStream2 = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest2 = new HttpRequest(inputStream2);
        assertThat((httpRequest2.parseRequestUri("GET http://localhost:8080/index.html HTTP/1.1")), is("http://localhost:8080/index.html"));

    }

    /*
    @Test
    public void requestLineからrequestUriをgetするテスト3() throws IOException {
        InputStream inputStream3 = new ByteArrayInputStream(" http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest3 = new HttpRequest(inputStream3);
        expectedException.expect(IOException.class);
        expectedException.expectMessage("不正なリクエストラインです。正しいリクエストラインを再度送信してください。");
        throw new RuntimeException("不正なリクエストラインです。正しいリクエストラインを再度送信してください。");
    }
    */

    @Test
    public void requestUriからUriPathを抜き出すのテスト() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest = new HttpRequest(inputStream);

        assertThat((httpRequest.parseUriPath("http://localhost:8080/index.html")), is("index.html"));
    }
}


