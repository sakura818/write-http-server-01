package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyContentTest Class
 *
 * @author sakura818
 */
public class HttpResponseTest {
    //TODO:

    private File file;
    private int statusCode;
    private OutputStream outputStream;

    HttpResponse httpResponse = new HttpResponse(outputStream, file, statusCode);

    @Test
    public void httpResponseを適切な形で生成できているかのテスト(File file, int statusCode) throws Exception {
        assertThat(httpResponse.createHttpResponseContents(file, statusCode), is("HTTP/1.1 200 OK".getBytes()));
    }


    public ArrayList<byte[]> createHttpResponseContentsTest() throws Exception {

        ArrayList<byte[]> createResponseContents = new ArrayList<byte[]>();
        {
            /** HttpResponseのMessageBodyの最後のflagとなるCRLFをバイト出力ストリームに書き込む OutputStreamクラスのwriteメソッドでは最後改行がされないため*/
            byte[] CRLF = "\r\n".getBytes("UTF-8");

            createResponseContents.add("HTTP/1.1 200 OK".getBytes());
            createResponseContents.add(("Server: sakura818\n" +
                    "Allow: GET\n" +
                    "Content-Language: en\n" +
                    "Content-Type: text/html; charset=UTF-8\n").getBytes());
            createResponseContents.add(messageBodyBuilder.build());
            createResponseContents.add(CRLF);
        }
        return createResponseContents;
    }


}
