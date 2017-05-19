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
        System.out.println(httpResponseStatusLineContent.createResponseStatusLine(statusCode));

        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent
                = new HttpResponseMessageHeaderContent(filePath);
        printWriter.println(httpResponseMessageHeaderContent.createResponseMessageHeader(filePath));

        System.out.println(httpResponseMessageHeaderContent.createResponseMessageHeader(filePath));

        HttpResponseMessageBodyContent httpResponseMessageBodyContent
                = new HttpResponseMessageBodyContent();
        outputStream.write(httpResponseMessageBodyContent.createResponseMessageBody(filePath, statusCode));

        for (int i = 0; i < httpResponseMessageBodyContent.createResponseMessageBody(filePath, statusCode).length; i++) {
            System.out.println(Integer.toHexString(httpResponseMessageBodyContent.createResponseMessageBody(filePath, statusCode)[i]));
        }

        byte[] CRLF = "\r\n".getBytes("UTF-8");
        outputStream.write(CRLF);
        for (int i = 0; i < CRLF.length; i++) {
            System.out.println(Integer.toHexString(CRLF[i]));
        }
    }

}



