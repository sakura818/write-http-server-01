package jp.co.topgate.sugawara.web;

import java.io.*;


/**
 * HttpResponseMessageBodyContent Class
 * HttpResponseのMessageBodyのContentを生成するクラス
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyContent {

    /**
     * statusCodeで条件分岐した後、filePathもしくはhttpResponseMessageBodyHtmlをバイト型の配列で読み込みHttpResponseMessageBodyを生成する
     *
     * @param filePath   ex:index.html
     * @param statusCode ex:200
     * @return httpResponseMessageBodyContent
     */

    public byte[] createHttpResponseMessageBody(File filePath, int statusCode) throws IOException {
        byte[] httpResponseMessageBodyContent = new byte[(int) filePath.length()];
        String httpResponseMessageBodyHtml;
        if (statusCode == 200) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
            try {
                bufferedInputStream.read(httpResponseMessageBodyContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedInputStream.close();
            }
        } else if (statusCode == 400) {
            httpResponseMessageBodyHtml = "<html><head><title>400 Bad Request</title></head>" +
                    "<body><h1>Bad Request</h1>" +
                    "<p>リクエストにエラーがあります。</p></body></html>";
            httpResponseMessageBodyContent = httpResponseMessageBodyHtml.getBytes();
            return httpResponseMessageBodyContent;
        } else if (statusCode == 404) {
            httpResponseMessageBodyHtml = "<html><head><title>404 Not Found</title></head>" +
                    "<body><h1>Not Found</h1>" +
                    "<p>該当のページは見つかりませんでした。</p></body></html>";
            httpResponseMessageBodyContent = httpResponseMessageBodyHtml.getBytes();
            return httpResponseMessageBodyContent;
        }
        return httpResponseMessageBodyContent;
    }
}
