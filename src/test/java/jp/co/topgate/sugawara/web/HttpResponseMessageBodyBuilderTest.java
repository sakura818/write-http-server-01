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
    public void statusCodeが404のときのテスト() throws IOException {

        File error404MessageBodyHtml = new File("src/test/resources/HttpResponseMessageBodyBuilderTest/error404MessageBodyHtml.txt");
        HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(error404MessageBodyHtml, 404);
        byte[] messageBody = new byte[(int) error404MessageBodyHtml.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(error404MessageBodyHtml));
        bufferedInputStream.read(messageBody);
        bufferedInputStream.close();
        byte[] statusCode404Html = {60, 104, 116, 109, 108, 62, 60, 104, 101, 97, 100, 62, 60, 116, 105, 116, 108, 101, 62, 52, 48, 52, 32, 78, 111, 116, 32, 70, 111, 117, 110, 100, 60, 47, 116, 105, 116, 108, 101, 62, 60, 47, 104, 101, 97, 100, 62, 60, 98, 111, 100, 121, 62, 60, 104, 49, 62, 78, 111, 116, 32, 70, 111, 117, 110, 100, 60, 47, 104, 49, 62, 60, 112, 62, -24, -87, -78, -27, -67, -109, -29, -127, -82, -29, -125, -102, -29, -125, -68, -29, -126, -72, -29, -127, -81, -24, -90, -117, -29, -127, -92, -29, -127, -117, -29, -126, -118, -29, -127, -66, -29, -127, -101, -29, -126, -109, -29, -127, -89, -29, -127, -105, -29, -127, -97, -29, -128, -126, 60, 47, 112, 62, 60, 47, 98, 111, 100, 121, 62, 60, 47, 104, 116, 109, 108, 62};
        assertThat(messageBody, is(statusCode404Html));

    }

    @Test
    public void statusCodeが200のときのテスト() throws IOException{

        File indexHtml = new File("src/test/resources/HttpResponseMessageBodyBuilderTest/index.html");
        HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(indexHtml, 404);
        byte[] binaryData = new byte[(int) indexHtml.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(indexHtml));
        bufferedInputStream.read(binaryData);
        bufferedInputStream.close();
        File filePath = new File("src/test/resources/HttpResponseMessageBodyBuilderTest/index.html");
        HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(filePath, 200);

    }

}
