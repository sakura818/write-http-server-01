package jp.co.topgate.sugawara.web;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
@RunWith(Enclosed.class)
public class BoardDynamicHttpResponseMessageHeaderBuilderTest {

    public static class buildメソッドのテスト {
        @Test
        public void レスポンスでhtmlファイルを返すときMessageHeaderを適切なシンタックスで生成するテスト() {
            File file = new File("src/test/resources/index.html");
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat(builder.build(), is(("Server: sakura818\n" +
                    "Allow: GET\n" +
                    "Content-Language: en\n" +
                    "Content-Type: text/html; charset=UTF-8\n\n").getBytes()));
        }
    }

    public static class createGeneralHeaderメソッドのテスト {
        @Test
        public void レスポンスでhtmlファイルを返すときGeneralHeaderを適切なシンタックスで生成するテスト() {
            File file = new File("src/test/resources/index.html");
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat(builder.createGeneralHeader(), is(""));
        }
    }

    public static class createResponseHeaderメソッドのテスト {
        @Test
        public void レスポンスでhtmlファイルを返すときmessageHeaderを適切なシンタックスで生成するテスト() {
            File file = new File("src/test/resources/index.html");
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat(builder.createResponseHeader(), is("Server: sakura818\n"));
        }
    }

    public static class createEntityHeaderメソッドのテスト {

        @Test
        public void レスポンスでhtmlファイルを返すときEntityHeaderを適切なシンタックスで生成するテスト() {
            File file = new File("src/test/resources/index.html");
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat(builder.createEntityHeader(file), is("Allow: GET\n" +
                    "Content-Language: en\n" +
                    "Content-Type: text/html; charset=UTF-8\n"));
        }
    }

    public static class catchContentTypeメソッドのテスト {
        @Test
        public void ファイルの拡張子htmlに対してContentTypeにtextスラッシュcssと文字エンコーディングUTF_8を返すテスト() {
            File file = new File("src/test/resources/index.html");
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseMessageHeaderBuilderTest.java
            StaticHttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new StaticHttpResponseMessageHeaderBuilder(file);
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(file)), is("text/html; charset=UTF-8"));
=======
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((builder.catchContentType(file)), is("text/html; charset=UTF-8"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseMessageHeaderBuilderTest.java
        }

        @Test
        public void ファイルの拡張子cssに対してContentTypeにtextスラッシュcssを返すテスト() {
            File file = new File("src/test/resources/sample.css");
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseMessageHeaderBuilderTest.java
            StaticHttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new StaticHttpResponseMessageHeaderBuilder(file);
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(file)), is("text/css"));
=======
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((builder.catchContentType(file)), is("text/css"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseMessageHeaderBuilderTest.java
        }

        @Test
        public void ファイルの拡張子jsに対しContentTypeにapplicationスラッシュjavascriptを返すテスト() {
            File file = new File("src/test/resources/sample.js");
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseMessageHeaderBuilderTest.java
            StaticHttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new StaticHttpResponseMessageHeaderBuilder(file);
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(file)), is("application/javascript"));
=======
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((builder.catchContentType(file)), is("application/javascript"));
        }

        @Test
        public void 未知のファイルの拡張子に対しContentTypeにapplicationスラッシュoctet_streamを返すテスト() {
            File file = new File("src/test/resources/sample.unknownExtract");
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((builder.catchContentType(file)), is("application/octet-stream"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseMessageHeaderBuilderTest.java
        }
    }

    public static class extractExtensionメソッドのテスト {
        @Test
        public void ドットを1つだけ含むファイルから拡張子htmlを抜き出すテスト() {
            File file = new File("src/test/resources/index.html");
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseMessageHeaderBuilderTest.java
            StaticHttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new StaticHttpResponseMessageHeaderBuilder(file);
            assertThat((httpResponseMessageHeaderBuilder.extractExtension(file)), is("html"));
=======
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((builder.extractExtension(file)), is("html"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseMessageHeaderBuilderTest.java
        }

        @Test
        public void ドットを2つ連続で含むファイルから拡張子を抜き出すテスト() {
            File file = new File("src/test/resources/index..html");
<<<<<<< HEAD:src/test/java/jp/co/topgate/sugawara/web/BoardDynamicHttpResponseMessageHeaderBuilderTest.java
            StaticHttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new StaticHttpResponseMessageHeaderBuilder(file);
            assertThat((httpResponseMessageHeaderBuilder.extractExtension(file)), is("html"));
=======
            HttpResponseMessageHeaderBuilder builder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((builder.extractExtension(file)), is("html"));
>>>>>>> 54b82c1e371e8b1d794e41763f6118f8e94b81ac:src/test/java/jp/co/topgate/sugawara/web/HttpResponseMessageHeaderBuilderTest.java
        }
    }

}
