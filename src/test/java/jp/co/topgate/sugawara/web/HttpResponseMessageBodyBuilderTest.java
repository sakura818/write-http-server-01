package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyBuilderTest Class
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyBuilderTest {

    @Test
    public void statusCodeが200のときのテスト() throws IOException {

        File indexHtml = new File("src/test/resources/index.html");
        HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(indexHtml);
        byte[] indexHtmlByteActual = new byte[(int) indexHtml.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(indexHtml));
        bufferedInputStream.read(indexHtmlByteActual);
        bufferedInputStream.close();
        assertThat(indexHtmlByteActual, is(("<!DOCTYPE html>\n" +
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
                "\n" +
                "    </script>\n" +
                "</p>\n" +
                "<p>pngです<img src=\"sample.png\" width=\"100\" height=\"100\" alt=\"png\" align=\"center\"></p>\n" +
                "<p>jpegです<img src=\"sample.jpeg\" width=\"100\" height=\"100\" alt=\"jpeg\" align=\"center\"></p>\n" +
                "<p>gifです<img src=\"sample.gif\" width=\"100\" height=\"100\" alt=\"gif\" border=\"0\" align=\"center\" hspace=\"10\" vspace=\"10\">\n" +
                "</p>\n" +
                "</body>\n" +
                "</html>").getBytes()));

    }

    @Test
    public void statusCodeが400のときのテスト() throws IOException {

        File badRequestHtml = new File("src/test/resources/BadRequest.html");
        HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(badRequestHtml);
        byte[] badRequestHtmlByteActual = new byte[(int) badRequestHtml.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(badRequestHtml));
        bufferedInputStream.read(badRequestHtmlByteActual);
        bufferedInputStream.close();
        assertThat(badRequestHtmlByteActual, is(("<!DOCTYPE html>\n" +
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
    public void statusCodeが404のときのテスト() throws IOException {

        File notFoundHtml = new File("src/test/resources/NotFound.html");
        HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(notFoundHtml);
        byte[] notFoundHtmlByteActual = new byte[(int) notFoundHtml.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(notFoundHtml));
        bufferedInputStream.read(notFoundHtmlByteActual);
        bufferedInputStream.close();
        assertThat(notFoundHtmlByteActual, is(("<!DOCTYPE html>\n" +
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
                "</html>").getBytes()));

    }

}
