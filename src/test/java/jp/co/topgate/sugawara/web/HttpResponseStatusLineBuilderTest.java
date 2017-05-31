package jp.co.topgate.sugawara.web;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseStatusLineBuilder Class
 * HttpResponseのStatusLineのContentを生成するクラスをテストする
 *
 * @author sakura818
 */

@RunWith(Enclosed.class)
public class HttpResponseStatusLineBuilderTest {

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

        @Test
        public void statusCodeが200のときに適切なreasonPhraseを返すかのテスト() {
            int statusCode = 200;
            HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("OK"));
        }

        @Test
        public void statusCodeが400のときに適切なreasonPhraseを返すかのテスト() {
            int statusCode = 400;
            HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Bad Request"));
        }

        @Test
        public void statusCodeが404のときに適切なreasonPhraseを返すかのテスト() {
            int statusCode = 404;
            HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Not Found"));
        }

        @Test
        public void statusCodeが501のときに適切なreasonPhraseを返すかのテスト() {
            int statusCode = 501;
            HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Not Implemented"));
        }

        @Test
        public void statusCodeが505のときに適切なreasonPhraseを返すかのテスト() {
            int statusCode = 505;
            HttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Http Version Not Supported"));
        }
    }


}
