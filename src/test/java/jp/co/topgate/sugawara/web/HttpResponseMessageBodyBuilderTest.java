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
    private int statusCode = 200;
    private File filePath = new File("src/test/resources/index.html");
    HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(filePath, statusCode);

    File noexistFile = new File("src/test/resources/noexist.txt");
    HttpResponseMessageBodyBuilder builder = new HttpResponseMessageBodyBuilder(noexistFile, 404);

    @Test
    public void statusCodeが404のときのテスト() throws IOException {
        /*
        byte[] messageBody = new byte[(int) this.filePath.length()];
        String messageBodyHtml;
        messageBodyHtml = "<html><head><title>404 Not Found</title></head>" +
                "<body><h1>Not Found</h1>" +
                "<p>該当のページは見つかりませんでした。</p></body></html>";
        messageBody = messageBodyHtml.getBytes();
        byte[] statusCode404Html = {60, 104, 116, 109, 108, 62, 60, 104, 101, 97, 100, 62, 60, 116, 105, 116, 108, 101, 62, 52, 48, 52, 32, 78, 111, 116, 32, 70, 111, 117, 110, 100, 60, 47, 116, 105, 116, 108, 101, 62, 60, 47, 104, 101, 97, 100, 62, 60, 98, 111, 100, 121, 62, 60, 104, 49, 62, 78, 111, 116, 32, 70, 111, 117, 110, 100, 60, 47, 104, 49, 62, 60, 112, 62, -24, -87, -78, -27, -67, -109, -29, -127, -82, -29, -125, -102, -29, -125, -68, -29, -126, -72, -29, -127, -81, -24, -90, -117, -29, -127, -92, -29, -127, -117, -29, -126, -118, -29, -127, -66, -29, -127, -101, -29, -126, -109, -29, -127, -89, -29, -127, -105, -29, -127, -97, -29, -128, -126, 60, 47, 112, 62, 60, 47, 98, 111, 100, 121, 62, 60, 47, 104, 116, 109, 108, 62};
        assertArrayEquals(statusCode404Html, messageBody);
       */

        404の結果 builder.build()

        byte[] binaryData = new byte[(int) noexistFile.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(noexistFile));
        bufferedInputStream.read(binaryData);
        bufferedInputStream.close();
        String binaryDataHex = null;
        for (int i = 0; i < binaryData.length; i++) {
            binaryDataHex = (Integer.toHexString(binaryData[i]));
        }
        /** sampleString.txtに書き込まれている文字列"ABC"は10進数で"65,66,67 16進数で"41,42,43"*/
        //String hoge = "41,42,43";
        //a// ssertThat(k, binaryDataHex);

    }

}
