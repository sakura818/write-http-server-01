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

    public static class buildメソッドのテスト {

        @Test
        public void StatusLineを適切な形で生成できているかのテスト() {
            assertThat(buildTest(), is("HTTP/1.1 200 OK\n".getBytes()));
        }

        public byte[] buildTest() {
            StringBuilder statusLine = new StringBuilder();
            statusLine.append("HTTP/1.1").append(" ");
            statusLine.append(200).append(" ");
            statusLine.append("OK").append("\n");
            return (statusLine.toString()).getBytes();
        }
    }

    public static class catchReasonPhraseメソッドのテスト {
        private int statusCode = 200;
        HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);

        @Test
        public void statusCodeが200のときに適切なreasonPhraseを返すかのテスト() {
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(200), is("OK"));
        }

        @Test
        public void statusCodeが400のときに適切なreasonPhraseを返すかのテスト() {
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(400), is("Bad Request"));
        }

        @Test
        public void statusCodeが404のときに適切なreasonPhraseを返すかのテスト() {
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(404), is("Not Found"));
        }

        @Test
        public void statusCodeが500のときに適切なreasonPhraseを返すかのテスト() {
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(500), is("Internal Server Error"));
        }
    }


}
