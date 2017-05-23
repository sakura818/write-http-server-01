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
    public void StatusLineを適切な形で生成できているかのテスト() {
        assertThat("HTTP/1.1 200 OK", is(buildTest()));
    }

    public String buildTest() {
        StringBuilder statusLine = new StringBuilder();
        statusLine.append("HTTP/1.1").append(" ");
        statusLine.append(200).append(" ");
        statusLine.append("OK");
        return statusLine.toString();
    }

    @Test
    public void statusCodeに応じて適切なreasonPhraseを返すテスト() {
        assertThat("OK", is(httpResponseStatusLineBuilder.getReasonPhrase(200)));
        assertThat("Not Found", is(httpResponseStatusLineBuilder.getReasonPhrase(404)));
    }

}
