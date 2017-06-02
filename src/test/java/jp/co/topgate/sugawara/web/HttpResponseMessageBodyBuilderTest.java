package jp.co.topgate.sugawara.web;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyBuilderTest Class
 *
 * @author sakura818
 */

@RunWith(Enclosed.class)
public class HttpResponseMessageBodyBuilderTest {
    public static class buildメソッドのテスト {

        @Test
        public void ステータスコード200を返すときメッセージボディにindexHtmlを送るテスト() throws IOException {
            File indexHtml = new File("src/test/resources/index.html");
            HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(indexHtml);
            assertThat(builder.build(), is(("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./sample.css\">\n" +
                    "    <script type=\"text/javascript\" src=\"./sample.js\"></script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>CSSでh1タグの文字を青色にします</h1>\n" +
                    "<p>JavaScriptでファイルの更新日時を表示します\n" +
                    "    <script>\n" +
                    "        lastModified_date();\n" +
                    "    </script>\n" +
                    "</p>\n" +
                    "<p>pngです<img src=\"sample.png\" width=\"100\" height=\"100\" alt=\"png\" align=\"center\"></p>\n" +
                    "<p>jpegです<img src=\"sample.jpeg\" width=\"100\" height=\"100\" alt=\"jpeg\" align=\"center\"></p>\n" +
                    "<p>gifです<img src=\"sample.gif\" width=\"100\" height=\"100\" alt=\"gif\" border=\"0\" align=\"center\" hspace=\"10\" vspace=\"10\">\n" +
                    "</p>\n" +
                    "</body>\n" +
                    "</html>\n").getBytes()));
        }

        @Test
        public void レスポンスにステータスコード400を返すときメッセージボディにbadRequestHtmlを送るテスト() throws IOException {
            File badRequestHtml = new File("src/test/resources/BadRequest.html");
            HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(badRequestHtml);
            assertThat(builder.build(), is(("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\n" +
                    "<head>\n" +
                    "    <title>400 Bad Request</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<h1>Not Found</h1>\n" +
                    "<p>リクエストにエラーがあります。</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>\n").getBytes()));
        }

        @Test
        public void レスポンスにステータスコード404を返すときメッセージボディにnotFoundHtmlを送るテスト() throws IOException {
            File notFoundHtml = new File("src/test/resources/NotFound.html");
            HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(notFoundHtml);
            assertThat(builder.build(), is(("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\n" +
                    "<head>\n" +
                    "    <title>404 Not Found</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<h1>Not Found</h1>\n" +
                    "<p>該当のページは見つかりませんでした。</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>\n").getBytes()));
        }


        @Test
        public void レスポンスにステータスコード501を返すときメッセージボディにnotImplementedHtmlを送るテスト() throws IOException {
            File notImplementedHtml = new File("src/test/resources/NotImplemented.html");
            HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(notImplementedHtml);
            assertThat(builder.build(), is(("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\n" +
                    "<head>\n" +
                    "    <title>501 Not Implemented</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<h1>Not Implemented</h1>\n" +
                    "<p>リクエストは正しいのですが、このサーバではリクエストされたメソッドをサポートしていません。</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>\n").getBytes()));
        }

        @Test
        public void レスポンスにステータスコード505を返すときメッセージボディにhttpVersionNotSupportedHtmlを送るテスト() throws IOException {
            File httpVersionNotSupportedHtml = new File("src/test/resources/HttpVersionNotSupported.html");
            HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(httpVersionNotSupportedHtml);
            assertThat(builder.build(), is(("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\n" +
                    "<head>\n" +
                    "    <title>505 Http Version Not Supported</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<h1>Http Version Not Supported</h1>\n" +
                    "<p>リクエストは正しいのですが、このサーバではリクエストされたHttpVersionをサポートしていません。</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>\n").getBytes()));
        }
    }

}
