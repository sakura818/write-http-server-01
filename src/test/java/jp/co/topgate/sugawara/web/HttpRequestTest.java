package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpRequestTest Class
 * リクエストを分解するのをテストするクラス
 *
 * @author sakura818
 */
public class HttpRequestTest {
    HttpRequest httpRequest = new HttpRequest();

    @Test
    public void 空白文字を区切り文字としてrequestLineを3つに分割できたかのテスト() {
        // requestLineがnullのとき
        httpRequest.spaceSeparateRequestLine(null);
        assertThat(null, is(httpRequest.spaceSeparateRequestLine()));

        // requestLineがGEThttp://localhost:8080/index.htmlのとき
        httpRequest.spaceSeparateRequestLine("GET  http://localhost:8080/index.html");
        assertThat(null, is(httpRequest.spaceSeparateRequestLine()));

        // requestLineがGET http://localhost:8080/index.html HTTP/1.1のとき
        httpRequest.spaceSeparateRequestLine("GET http://localhost:8080/index.html HTTP/1.1");
        assertThat("index.html", is(httpRequest.spaceSeparateRequestLine()));
    }

    @Test
    public void requestUriから正しくfileを抜き出せるかのテスト() {
        // requestUriがnullのとき
        assertThat(null, is(httpRequest.parseFile(null)));

        // requestUriが空のとき
        assertThat("/", is(httpRequest.parseFile("")));

        // requestUriがhttp://localhost:8080/index.htmlのとき
        assertThat("index.html", is(httpRequest.parseFile("http://localhost:8080/index.html")));

        // requestUriがhttp://localhost:8080/hoge/index.htmlのとき
        assertThat(null, is(httpRequest.parseFile("http://localhost:8080/hoge/index.html")));
    }


    @Test
    public void HttpRequestに応じて適切なステータスコードを返せるかのテスト() {
        // requestLineがnullのとき
        assertThat(400, is(httpRequest.selectStatusCode(null,null)));

        // requestLineがGET http://localhost:8080/index.html HTTP/1.1のとき
        assertThat(404, is(httpRequest.selectStatusCode("GET","apple.html")));

        // requestLineがGET  http://localhost:8080/index.htmlのとき
        assertThat(200, is(httpRequest.selectStatusCode("GET","index.html")));
    }

}


