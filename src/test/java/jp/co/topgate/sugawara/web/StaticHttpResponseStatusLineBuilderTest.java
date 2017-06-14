package jp.co.topgate.sugawara.web;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * StaticHttpResponseStatusLineBuilder Class
 * HttpResponseのStatusLineのContentを生成するクラスをテストする
 *
 * @author sakura818
 */

@RunWith(Enclosed.class)
public class StaticHttpResponseStatusLineBuilderTest {

    public static class buildメソッドのテスト {

        @Test
        public void ステータスコード200のときにStatusLineを適切なシンタックスで生成するテスト() {
            StaticHttpResponseStatusLineBuilder builder = new StaticHttpResponseStatusLineBuilder(200);
            assertThat(builder.build(), is("HTTP/1.1 200 OK\n".getBytes()));
        }
    }

    public static class catchReasonPhraseメソッドのテスト {

        @Test
        public void ステータスコード200のときにreasonPhraseにOKを返すテスト() {
            int statusCode = 200;
            StaticHttpResponseStatusLineBuilder builder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("OK"));
        }

        @Test
        public void ステータスコード400のときにreasonPhraseにBad_Requestを返すテスト() {
            int statusCode = 400;
            StaticHttpResponseStatusLineBuilder builder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Bad Request"));
        }

        @Test
        public void ステータスコード404のときにNot_Foundを返すテスト() {
            int statusCode = 404;
            StaticHttpResponseStatusLineBuilder builder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Not Found"));
        }

        @Test
        public void ステータスコード501のときにreasonPhraseにNot_Implementedを返すテスト() {
            int statusCode = 501;
            StaticHttpResponseStatusLineBuilder builder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Not Implemented"));
        }

        @Test
        public void ステータスコード505のときにreasonPhraseにHttp_Version_Not_Supportedを返すテスト() {
            int statusCode = 505;
            StaticHttpResponseStatusLineBuilder builder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Http Version Not Supported"));
        }
    }


}
