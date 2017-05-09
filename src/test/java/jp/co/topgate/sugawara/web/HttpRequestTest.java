package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/04/19.
 *
 * @author sakura818
 */
public class HttpRequestTest {
    HttpRequest httpRequest = new HttpRequest();

    @Test
    public void requestUriから正しくfilePathを抜き出せるかのテスト() {
        // requestUriがnullのとき
        assertThat(null, is(httpRequest.partRequestUriPath(null)));

        // requestUriが空のとき
        assertThat("/", is(httpRequest.partRequestUriPath("")));

        // requestUriがhttp://localhost:8080/hello.htmlのとき
        assertThat("hello.html", is(httpRequest.partRequestUriPath("http://localhost:8080/hello.html")));

        // requestUriがhttp://localhost:8080/hoge/hello.htmlのとき
        assertThat(null, is(httpRequest.partRequestUriPath("http://localhost:8080/hoge/hello.html")));
    }

    @Test
    public void 空白文字を区切り文字としてrequestLineを3つに分割できたかのテスト() {
        // requestLineがnullのとき
        httpRequest.spaceSeparateRequestLine(null);
        assertThat(null, is(httpRequest.getMethod()));
        assertThat(null, is(httpRequest.getFilePath()));

        // requestLineがGET http://localhost:8080/hello.html HTTP/1.1のとき
        httpRequest.spaceSeparateRequestLine("GET http://localhost:8080/hello.html HTTP/1.1");
        assertThat("GET", is(httpRequest.getMethod()));
        assertThat("hello.htm", is(httpRequest.getFilePath()));

        // requestLineがGET  http://localhost:8080/hello.htmlのとき
        httpRequest.spaceSeparateRequestLine("GET  http://localhost:8080/hello.html");
        assertThat(null, is(httpRequest.getMethod()));
        assertThat(null, is(httpRequest.getFilePath()));
    }

}


