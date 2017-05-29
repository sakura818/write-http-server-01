package jp.co.topgate.sugawara.web;


import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyContentTest Class
 *
 * @author sakura818
 */
public class HttpResponseTest {

    private File file;
    private int statusCode;
    private OutputStream outputStream;

    HttpResponse httpResponse = new HttpResponse(file, statusCode);

    @Test
    public void httpResponseを適切な形で生成できているかのテスト() throws Exception {
        assertArrayEquals(httpResponseActual(), httpResponseExpect());
    }

    public byte[] httpResponseExpect() throws Exception {

        byte[] httpResponseExpect = null;

        byte[] statusLine = "HTTP/1.1 200 OK".getBytes();
        byte[] messageHeader = ("Server: sakura818\n" +
                "Allow: GET\n" +
                "Content-Language: en\n" +
                "Content-Type: text/html; charset=UTF-8\n").getBytes();
        byte[] messageBody = ("<!DOCTYPE html>\n" +
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
                "</html>").getBytes();
        byte[] CRLF = "\r\n".getBytes("UTF-8");
        int statusLineLength = statusLine.length;
        int messageHeaderLength = messageHeader.length;
        int messageBodyLength = messageBody.length;
        int CRLFLength = CRLF.length;

        byte[] createResponseContents = new byte[statusLineLength + messageHeaderLength + messageBodyLength + CRLFLength];

        System.arraycopy(statusLine, 0, createResponseContents, 0, statusLineLength);
        System.arraycopy(messageHeader, 0, createResponseContents, statusLineLength, messageHeaderLength);
        System.arraycopy(messageBody, 0, createResponseContents, (statusLineLength + messageHeaderLength), messageBodyLength);
        System.arraycopy(CRLF, 0, createResponseContents, (statusLineLength + messageHeaderLength + messageBodyLength), CRLFLength);

        return httpResponseExpect;
    }


    public byte[] httpResponseActual() throws Exception {

        byte[] httpResponseActual = ("HTTP/1.1 200 OK" + "Server: sakura818\n" +
                    "Allow: GET\n" +
                    "Content-Language: en\n" +
                    "Content-Type: text/html; charset=UTF-8\n" + "<!DOCTYPE html>\n" +
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
                    "</html>" + "\r\n").getBytes();

        return httpResponseActual;
    }


}
