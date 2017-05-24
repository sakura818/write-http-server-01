package jp.co.topgate.sugawara.web;

import org.junit.Test;

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

    @Test
    //TODO:このメソッドはテスト対象メソッドがまだ未実装の部分があるため書いている途中
    public void requestLineからrequestUriをgetするテスト() throws IOException {
        /** 以下の3行はhttpRequestのインスタンスを呼び出す エラー処理がクラス全体ではなくメソッドでしかできないため */
        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

        assertThat("http://localhost:8080/index.html", is(httpRequest.getRequestUri("GET http://localhost:8080/index.html HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("GET /index.html HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("hoge /index.html HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("GET /index.html hoge")));
        assertThat("/index.html", is(httpRequest.getRequestUri("GET /index.html HTTP/1.1 HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("")));
        assertThat("/index.html", is(httpRequest.getRequestUri("GET HTTP/1.1")));
        assertThat("/index.html", is(httpRequest.getRequestUri("/index.html")));
        assertThat("/index.html", is(httpRequest.getRequestUri("/index.html")));
    }

    @Test
    public void requestUriからUriPathを抜き出すのテスト() throws IOException {
        /** 以下の3行はhttpRequestのインスタンスを呼び出す エラー処理がクラス全体ではなくメソッドでしかできないため */
        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

        assertThat("index.html", is(httpRequest.parseUriPath("http://localhost:8080/index.html")));
        assertThat("index.html", is(httpRequest.parseUriPath("/index.html")));
        assertThat("index/", is(httpRequest.parseUriPath("index/")));
        assertThat("index/", is(httpRequest.parseUriPath("index")));
        assertThat("index/", is(httpRequest.parseUriPath("//")));
        assertThat("index/", is(httpRequest.parseUriPath("/")));
    }


}


