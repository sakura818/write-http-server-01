package jp.co.topgate.sugawara.web;

import org.junit.Test;

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
        // TODO assertThat(200, is(httpResponseStatusLineContent.getStatusCode()));
        // TODO assertThat("OK", is(httpResponseStatusLineContent.getReasonPhrase()));
        // TODO assertThat("200 OK", is(httpResponseStatusLineContent.getReasonPhrase()));
    }

    @Test
    public void HttpResponseStatusLineContentを400にしたときのテスト() {
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent();

        httpResponseStatusLineContent.setStatusCode(400);
        // TODO assertThat(400, is(httpResponseStatusLineContent.getStatusCode()));
        // TODO assertThat("Bad Request", is(httpResponseStatusLineContent.getReasonPhrase()));
        // TODO assertThat("400 Bad Request", is(httpResponseStatusLineContent.getReasonPhrase()));
    }

    @Test
    public void HttpResponseStatusLineContentを404にしたときのテスト() {
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent();

        httpResponseStatusLineContent.setStatusCode(404);
        // TODO assertThat(404, is(httpResponseStatusLineContent.getStatusCode()));
        // TODO assertThat("Not Found", is(httpResponseStatusLineContent.getReasonPhrase()));
        // TODO assertThat("404 Not Found", is(httpResponseStatusLineContent.getReasonPhrase()));
    }


}
