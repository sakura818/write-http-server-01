package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseStatusLineContentTest Class
 * HttpResponseのStatusLineのContentを生成するクラスをテストする
 *
 * @author sakura818
 */


public class HttpResponseStatusLineContentTest {
    private int statusCode;
    HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent(this.statusCode);

    @Test
    public void statusCodeに応じて適切なreasonPhraseを返すテスト() {
        assertThat("OK", is(httpResponseStatusLineContent.createReasonPhrase(200)));
        assertThat("Bad Request", is(httpResponseStatusLineContent.createReasonPhrase(400)));
        assertThat("Not Found", is(httpResponseStatusLineContent.createReasonPhrase(404)));
    }

    @Test
    public void StatusLineをstringBuilderで文字列を連結させるテスト() {
        assertThat("HTTP/1.1 200 OK", is(httpResponseStatusLineContent.createHttpResponseStatusLine(200)));
    }

}
