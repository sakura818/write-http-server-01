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
            HttpResponseStatusLineBuilder builder = new HttpResponseStatusLineBuilder(200);
            assertThat(builder.build(), is("HTTP/1.1 200 OK\n".getBytes()));
        }
    }

    public static class catchReasonPhraseメソッドのテスト {

        @Test
        public void ステータスコード200のときにreasonPhraseにOKを返すテスト() {
            int statusCode = 200;
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseStatusLineBuilderTest.java
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("OK"));
=======
            HttpResponseStatusLineBuilder builder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("OK"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseStatusLineBuilderTest.java
        }

        @Test
        public void ステータスコード400のときにreasonPhraseにBad_Requestを返すテスト() {
            int statusCode = 400;
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseStatusLineBuilderTest.java
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Bad Request"));
=======
            HttpResponseStatusLineBuilder builder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Bad Request"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseStatusLineBuilderTest.java
        }

        @Test
        public void ステータスコード404のときにNot_Foundを返すテスト() {
            int statusCode = 404;
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseStatusLineBuilderTest.java
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Not Found"));
=======
            HttpResponseStatusLineBuilder builder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Not Found"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseStatusLineBuilderTest.java
        }

        @Test
        public void ステータスコード501のときにreasonPhraseにNot_Implementedを返すテスト() {
            int statusCode = 501;
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseStatusLineBuilderTest.java
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Not Implemented"));
=======
            HttpResponseStatusLineBuilder builder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Not Implemented"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseStatusLineBuilderTest.java
        }

        @Test
        public void ステータスコード505のときにreasonPhraseにHttp_Version_Not_Supportedを返すテスト() {
            int statusCode = 505;
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseStatusLineBuilderTest.java
            StaticHttpResponseStatusLineBuilder httpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
            assertThat(httpResponseStatusLineBuilder.catchReasonPhrase(statusCode), is("Http Version Not Supported"));
=======
            HttpResponseStatusLineBuilder builder = new HttpResponseStatusLineBuilder(statusCode);
            assertThat(builder.catchReasonPhrase(statusCode), is("Http Version Not Supported"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseStatusLineBuilderTest.java
        }
    }
}
