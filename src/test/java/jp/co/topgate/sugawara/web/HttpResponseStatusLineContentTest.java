package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseStatusLineContentTest Class
 * リクエストに応じて適切なステータスコードを判断するのをテストするクラス
 * @author sakura818
 */


public class HttpResponseStatusLineContentTest {
    @Test
    public void HttpResponseStatusLineContentを200にしたときのテスト() {
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent();

        httpResponseStatusLineContent.setStatusCode(200);
        assertThat(200, is(httpResponseStatusLineContent.getStatusCode()));
        assertThat("OK", is(httpResponseStatusLineContent.getReasonPhrase()));
        assertThat("200 OK", is(httpResponseStatusLineContent.getReasonPhrase()));
    }

    @Test
    public void HttpResponseStatusLineContentを400にしたときのテスト() {
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent();

        httpResponseStatusLineContent.setStatusCode(400);
        assertThat(400, is(httpResponseStatusLineContent.getStatusCode()));
        assertThat("Bad Request", is(httpResponseStatusLineContent.getReasonPhrase()));
        assertThat("400 Bad Request", is(httpResponseStatusLineContent.getReasonPhrase()));
    }

    @Test
    public void HttpResponseStatusLineContentを404にしたときのテスト() {
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent();

        httpResponseStatusLineContent.setStatusCode(404);
        assertThat(404, is(httpResponseStatusLineContent.getStatusCode()));
        assertThat("Not Found", is(httpResponseStatusLineContent.getReasonPhrase()));
        assertThat("404 Not Found", is(httpResponseStatusLineContent.getReasonPhrase()));
    }


}
