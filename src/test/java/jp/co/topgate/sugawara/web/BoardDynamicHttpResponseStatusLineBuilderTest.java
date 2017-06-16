package jp.co.topgate.sugawara.web;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
@RunWith(Enclosed.class)
public class BoardDynamicHttpResponseStatusLineBuilderTest {

    public static class buildメソッドのテスト {

        @Test
        public void ステータスコード200のときにStatusLineを適切なシンタックスで生成するテスト() {
            BoardDynamicHttpResponseStatusLineBuilder builder = new BoardDynamicHttpResponseStatusLineBuilder(200);
            assertThat(builder.build(), is("HTTP/1.1 200 OK\n".getBytes()));
        }
    }

    public static class catchReasonPhraseメソッドのテスト {

        @Test
        public void ステータスコード200のときにreasonPhraseにOKを返すテスト() {
            int statusCode = 200;
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("OK"));

        }

        @Test
        public void ステータスコード400のときにreasonPhraseにBad_Requestを返すテスト() {
            int statusCode = 400;
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Bad Request"));

        }

        @Test
        public void ステータスコード404のときにNot_Foundを返すテスト() {
            int statusCode = 404;
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Not Found"));

        }

        @Test
        public void ステータスコード501のときにreasonPhraseにNot_Implementedを返すテスト() {
            int statusCode = 501;
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Not Implemented"));
        }

        @Test
        public void ステータスコード505のときにreasonPhraseにHttp_Version_Not_Supportedを返すテスト() {
            int statusCode = 505;
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Http Version Not Supported"));

        }
    }
}
