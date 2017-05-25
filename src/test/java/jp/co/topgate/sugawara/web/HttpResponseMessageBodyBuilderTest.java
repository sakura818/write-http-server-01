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
    private File filePath;
    private int statusCode;

    public HttpResponseMessageBodyBuilderTest(File filePath, int statusCode) {
        this.filePath = filePath;
        this.statusCode = statusCode;
    }

    @Test
    public void statusCodeが200のときのテスト() throws IOException{

        File indexHtml = new File("src/test/resources/HttpResponseMessageBodyBuilderTest/index.html");
        HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(indexHtml);
        byte[] messageBody = new byte[(int) indexHtml.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(indexHtml));
        bufferedInputStream.read(messageBody);
        bufferedInputStream.close();
        byte[] indexHtmlExpectByte  = {};
        assertThat(messageBody, is(indexHtmlExpectByte));

    }

    @Test
    public void statusCodeが400のときのテスト() throws IOException{

        File statusCode400Html = new File("src/test/resources/HttpResponseMessageBodyBuilderTest/statusCode400.html");
        HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(statusCode400Html);
        byte[] messageBody = new byte[(int) statusCode400Html.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(statusCode400Html));
        bufferedInputStream.read(messageBody);
        bufferedInputStream.close();
        byte[] statusCode400HtmlExpectByte  = {};
        assertThat(messageBody, is(statusCode400HtmlExpectByte));

    }


    @Test
    public void statusCodeが404のときのテスト() throws IOException {

        File statusCode404Html = new File("src/test/resources/HttpResponseMessageBodyBuilderTest/statusCode404.html");
        HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(statusCode404Html);
        byte[] messageBody = new byte[(int) statusCode404Html.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(statusCode404Html));
        bufferedInputStream.read(messageBody);
        bufferedInputStream.close();
        byte[] statusCode404HtmlExpectByte = {};
        assertThat(messageBody, is(statusCode404HtmlExpectByte));

    }

}
