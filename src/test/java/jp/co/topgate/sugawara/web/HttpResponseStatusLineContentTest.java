package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseStatusLineContentTest Class
 * リクエストに応じて適切なステータスコードを判断するのをテストするクラス
 *
 * @author sakura818
 */


public class HttpResponseStatusLineContentTest {
    private int statusCode;
    @Test
    public void ステータスコードに応じて適切なreasonPhraseを返すテスト() {

        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent(statusCode);

        httpResponseStatusLineContent.setStatusCode(200);
        assertThat("OK", is(httpResponseStatusLineContent.createReasonPhrase(statusCode)));

        httpResponseStatusLineContent.setStatusCode(400);
        assertThat("Bad Request", is(httpResponseStatusLineContent.createReasonPhrase(statusCode)));

        httpResponseStatusLineContent.setStatusCode(404);
        assertThat("Not Found", is(httpResponseStatusLineContent.createReasonPhrase(statusCode)));
    }


}
