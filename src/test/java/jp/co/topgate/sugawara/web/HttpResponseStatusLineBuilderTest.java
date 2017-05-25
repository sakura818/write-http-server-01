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
    private int statusCode = 200;
    HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);

    @Test
    public void StatusLineを適切な形で生成できているかのテスト() {
        assertThat("HTTP/1.1 200 OK".getBytes(), is(buildTest()));
    }

    public byte[] buildTest() {
        StringBuilder statusLine = new StringBuilder();
        statusLine.append("HTTP/1.1").append(" ");
        statusLine.append(200).append(" ");
        statusLine.append("OK");
        return (statusLine.toString()).getBytes();
    }

    @Test
    public void statusCodeに応じて適切なreasonPhraseを返すテスト() {
        assertThat(httpResponseStatusLineBuilder.getReasonPhrase(200), is("OK"));
        assertThat(httpResponseStatusLineBuilder.getReasonPhrase(400), is("Bad Request"));
        assertThat(httpResponseStatusLineBuilder.getReasonPhrase(404), is("Not Found"));
    }

}
