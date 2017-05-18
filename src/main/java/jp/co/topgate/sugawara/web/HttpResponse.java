package jp.co.topgate.sugawara.web;

import java.io.*;

/**
 * HttpResponse class
 * HttpResponseを出力ストリームOutputStreamに送信する
 * HttpResponse = HttpResponseStatusLineContent Class
 * + HttpResponseMessageHeaderContent Class
 * + HttpResponseBodyContent Class
 *
 * @author sakura818
 */

public class HttpResponse {

    private String responseBodyTextFile;
    private String responseBodyBinaryFile;

    /**
     * 生成したレスポンスのコンテンツをOutputStreamに書き込む
     * 生成したレスポンスのコンテンツ
     * = HttpResponseStatusLineContent + HttpResponseMessageHeaderContent + HttpResponseBodyContent
     *
     * @param outputStream 書き込み先データストリーム
     * @throws IOException
     */
    public void writeResponseOutputStream(OutputStream outputStream, File filePath, int statusCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        System.out.println("response...");

        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent(statusCode);
        printWriter.println(httpResponseStatusLineContent.createResponseStatusLine(statusCode));

        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent
                = new HttpResponseMessageHeaderContent(filePath);
        printWriter.println(httpResponseMessageHeaderContent.createResponseMessageHeader(filePath));

        HttpResponseMessageBodyContent httpResponseMessageBodyContent
                = new HttpResponseMessageBodyContent();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        bufferedOutputStream.write(httpResponseMessageBodyContent.createResponseMessageBody(filePath, statusCode));
        byte[] CRLF = "\r\n".getBytes("utf-8");
        bufferedOutputStream.write(CRLF);
    }

}



