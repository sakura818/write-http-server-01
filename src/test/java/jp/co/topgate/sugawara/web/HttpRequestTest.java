package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/04/19.
 */
public class HttpRequestTest {
    HttpRequest httpRequest = new HttpRequest();

    @Test
    public void requestUriから正しくパス名を抜き出せるかのテスト() {
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
    public void 空白文字を区切り文字としてrequestLineを3つに分割することができるかのテスト() {
        // requestUriがnullのとき
        assertThat(null, is(httpRequest.spaceSeparateRequestLine(null)));

        // requestUriがGET hello.html HTTP/1.1のとき
        assertThat(null, is(httpRequest.spaceSeparateRequestLine("GET hello.html HTTP/1.1")));

        // requestUriがGET hello.html HTTP/1.1 のとき
        assertThat(null, is(httpRequest.spaceSeparateRequestLine("GET hello.html HTTP/1.1 ")));


    }

}


