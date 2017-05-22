package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseStatusLineBuilder Class
 * HttpResponseのStatusLineのContentを生成するクラスをテストする
 *
 * @author sakura818
 */


public class HttpResponseStatusLineBuilderTest {
    private int statusCode;

    HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);

    @Test
    public void statusCodeに応じて適切なreasonPhraseを返すテスト() {
        assertThat("OK", is(httpResponseStatusLineBuilder.getReasonPhrase(statusCode)));
        assertThat("Bad Request", is(httpResponseStatusLineBuilder.getReasonPhrase(400)));
        assertThat("Not Found", is(httpResponseStatusLineBuilder.getReasonPhrase(404)));
    }

    @Test
    public void StatusLineをstringBuilderで文字列を連結させるテスト() {
        assertThat("HTTP/1.1 200 OK", is(httpResponseStatusLineBuilder.build(statusCode)));
    }

}
