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

        String[] requestLineArray2 = httpRequest.spaceSeparateRequestLine("GET / HTTP/1.1");
        String method2 = requestLineArray2[0];
        String requestUri2 = requestLineArray2[1];
        assertThat("GET", is(method2));
        assertThat("/", is(requestUri2));

        String[] requestLineArray3 = httpRequest.spaceSeparateRequestLine("GET http://localhost:8080/index.html HTTP/1.1");
        String method3 = requestLineArray3[0];
        String requestUri3 = requestLineArray3[1];
        assertThat("GET", is(method3));
        assertThat("http://localhost:8080/index.html", is(requestUri3));

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


