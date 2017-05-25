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
        //File DummyHttpRequest1 = new File("src/test/resources/DummyHttpRequestLine/1.txt");
        InputStream inputStream1 = new ByteArrayInputStream("GET /index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest1 = new HttpRequest(inputStream1);
        assertThat((httpRequest1.parseRequestUri("GET /index.html HTTP/1.1")), is("/index.html"));

        InputStream inputStream2 = new ByteArrayInputStream("GET http://localhost:8080/index.html HTTP/1.1".getBytes("utf-8"));
        HttpRequest httpRequest2 = new HttpRequest(inputStream2);
        assertThat((httpRequest2.parseRequestUri("GET http://localhost:8080/index.html HTTP/1.1")), is("http://localhost:8080/index.html"));

        /*
        File DummyHttpRequest2 = new File("src/test/resources/DummyHttpRequestLine/2.txt");
        InputStream inputStream2 = new FileInputStream(DummyHttpRequest2);
        HttpRequest httpRequest2 = new HttpRequest(inputStream2);
        assertThat("http://localhost:8080/index.html", is(httpRequest2.getRequestUri("GET http://localhost:8080/index.html HTTP/1.1")));
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
    public void requestUriからUriPathを抜き出すのテスト() throws IOException {
        /** 以下の3行はhttpRequestのインスタンスを呼び出す エラー処理がクラス全体ではなくメソッドでしかできないため */
        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequestLine.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

        assertThat((httpRequest.parseUriPath("http://localhost:8080/index.html")), is("index.html"));
        assertThat((httpRequest.parseUriPath("/index.html")), is("index.html"));
        assertThat((httpRequest.parseUriPath("index")), is("index/"));
    }


}


