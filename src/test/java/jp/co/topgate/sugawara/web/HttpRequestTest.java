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
    public void requestLineを正しく分割できているかのテスト() throws IOException {

        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

        String[] requestLineArray = httpRequest.splitRequestLine("GET / HTTP/1.1");
        String method = requestLineArray[0];
        String requestUri = requestLineArray[1];
        assertThat("GET", is(method));
        assertThat("/", is(requestUri));

        String[] requestLineArray2 = httpRequest.splitRequestLine("GET http://localhost:8080/index.html HTTP/1.1");
        String method2 = requestLineArray2[0];
        String requestUri2 = requestLineArray2[1];
        assertThat("GET", is(method2));
        assertThat("http://localhost:8080/index.html", is(requestUri2));
    }

    @Test
    public void requestUriから正しくfileを抜き出せるかのテスト() throws IOException {

        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);
        // assertThat("index.html", is(httpRequest.parseFilePath("/")));
        assertThat("index.html", is(httpRequest.parseFilePath("http://localhost:8080/index.html")));
        // assertThat("index.html", is(httpRequest.parseFilePath("/")));
    }


}


