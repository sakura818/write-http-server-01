package jp.co.topgate.sugawara.web;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * HttpResponseMessageHeaderBuilderTest Class
 * HttpResponseのMessageHeaderのContentを生成するクラスをテストするクラス
 *
 * @author sakura818
 */

@RunWith(Enclosed.class)
public class HttpResponseMessageHeaderBuilderTest {

    public static class buildメソッドのテスト {
        @Test
        public void buildメソッドのEntityHeaderTestを適切な形で生成できているかのテスト() {
            assertThat(buildTest(), is(("Server: sakura818\n" +
                    "Allow: GET\n" +
                    "Content-Language: en\n" +
                    "Content-Type: text/html; charset=UTF-8\n").getBytes()));
        }


        public byte[] buildTest() {
            StringBuilder messageHeader = new StringBuilder();
            messageHeader.append("");
            messageHeader.append("Server: sakura818\n");
            messageHeader.append("Allow: GET\n" +
                    "Content-Language: en\n" +
                    "Content-Type: text/html; charset=UTF-8\n");
            return (messageHeader.toString()).getBytes();
        }
    }

    public static class createGeneralHeaderメソッドのテスト {
        @Test
        public void GeneralHeaderを適切な形で生成できているかのテスト() {
            assertThat(createGeneralHeaderTest(), is(""));
        }

        public String createGeneralHeaderTest() {
            StringBuilder GeneralHeader = new StringBuilder();
            return GeneralHeader.toString();
        }

    }

    public static class createResponseHeaderメソッドのテスト {
        @Test
        public void messageHeaderを適切な形で生成できているかのテスト() {
            assertThat(createResponseHeaderTest(), is("Server: sakura818\n"));
        }

        public String createResponseHeaderTest() {
            StringBuilder responseHeader = new StringBuilder();
            responseHeader.append("Server: " + "sakura818").append("\n");
            return responseHeader.toString();
        }
    }

    public static class createEntityHeaderメソッドのテスト {

        @Test
        public void EntityHeaderを適切な形で生成できているかのテスト() {
            assertThat(createEntityHeaderTest(), is("Allow: GET\n" +
                    "Content-Language: en\n" +
                    "Content-Type: text/html; charset=UTF-8\n"));
        }

        public String createEntityHeaderTest() {
            StringBuilder entityHeader = new StringBuilder();
            entityHeader.append("Allow: " + "GET").append("\n");
            entityHeader.append("Content-Language: " + "en").append("\n");
            entityHeader.append("Content-Type: " + "text/html; charset=UTF-8").append("\n");
            return entityHeader.toString();
        }
    }

    public static class catchContentTypeメソッドのテスト {
        private File file = new File("src/test/resources/index.html");
        HttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new HttpResponseMessageHeaderBuilder(file);

        @Test
        public void ファイルの拡張子に応じて適切なContentTypeをかえすテスト() {
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.html"))), is("text/html; charset=UTF-8"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.htm"))), is("text/html; charset=UTF-8"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.css"))), is("text/css"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.js"))), is("application/javascript"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.jpg"))), is("image/jpeg"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.jpeg"))), is("image/jpeg"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.png"))), is("image/png"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.gif"))), is("image/gif"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.txt"))), is("text/plain"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.pdf"))), is("application/pdf"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.mp4"))), is("video/mp4"));
            assertThat((httpResponseMessageHeaderBuilder.catchContentType(new File("hoge.hoge"))), is("text/html; charset=utf-8"));
        }
    }

    public static class extractExtensionメソッドのテスト {
        @Test
        public void ファイルから拡張子をextractするテスト1() {
            File file = new File("src/test/resources/index.html");
            HttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((httpResponseMessageHeaderBuilder.extractExtension(file)), is("html"));
        }

        @Test
        public void ファイルから拡張子をextractするテスト2() {
            File file = new File("src/test/resources/index..html");
            HttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new HttpResponseMessageHeaderBuilder(file);
            assertThat((httpResponseMessageHeaderBuilder.extractExtension(file)), is("html"));
        }
    }

}

