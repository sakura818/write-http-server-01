package jp.co.topgate.sugawara.web;


import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyContentTest Class
 *
 * @author sakura818
 */

@RunWith(Enclosed.class)
public class StaticHttpResponseTest {

    public static class createHttpResponseContentメソッドのテスト {

        @Test
        public void httpResponseを適切な形で生成できているかのテスト() throws Exception {
            assertThat(httpResponseActual(), is(httpResponseExpect()));
        }

        public byte[] httpResponseExpect() throws Exception {

            byte[] statusLine = "HTTP/1.1 200 OK\n".getBytes();
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
            int statusLineLength = statusLine.length;
            int messageHeaderLength = messageHeader.length;
            int messageBodyLength = messageBody.length;

            byte[] httpResponseExpect = new byte[statusLineLength + messageHeaderLength + messageBodyLength];

            System.arraycopy(statusLine, 0, httpResponseExpect, 0, statusLineLength);
            System.arraycopy(messageHeader, 0, httpResponseExpect, statusLineLength, messageHeaderLength);
            System.arraycopy(messageBody, 0, httpResponseExpect, (statusLineLength + messageHeaderLength), messageBodyLength);

            return httpResponseExpect;
        }


        public byte[] httpResponseActual() throws Exception {

            byte[] httpResponseActual = ("HTTP/1.1 200 OK\n" + "Server: sakura818\n" +
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
                    "</html>").getBytes();

            return httpResponseActual;
        }
    }


}
