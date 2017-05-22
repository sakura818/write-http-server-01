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

    /**
     * 生成したレスポンスのコンテンツをOutputStreamに書き込む
     * 生成したレスポンスのコンテンツ
     * = HttpResponseStatusLineContent + HttpResponseMessageHeaderContent + HttpResponseBodyContent
     *
     * @param outputStream バイト出力ストリーム
     * @throws IOException
     */
    public void writeResponseOutputStream(OutputStream outputStream, File filePath, int statusCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        System.out.println("http response...");

        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent(statusCode);
        printWriter.println(httpResponseStatusLineContent.createHttpResponseStatusLine(statusCode));
        System.out.println(httpResponseStatusLineContent.createHttpResponseStatusLine(statusCode));

        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent
                = new HttpResponseMessageHeaderContent(filePath);
        printWriter.println(httpResponseMessageHeaderContent.createHttpResponseMessageHeader(filePath));

        System.out.println(httpResponseMessageHeaderContent.createHttpResponseMessageHeader(filePath));

        HttpResponseMessageBodyContent httpResponseMessageBodyContent
                = new HttpResponseMessageBodyContent();
        outputStream.write(httpResponseMessageBodyContent.createHttpResponseMessageBody(filePath, statusCode));
        for (int i = 0; i < httpResponseMessageBodyContent.createHttpResponseMessageBody(filePath, statusCode).length; i++) {
            System.out.println(Integer.toHexString(httpResponseMessageBodyContent.createHttpResponseMessageBody(filePath, statusCode)[i]));
        }

        byte[] CRLF = "\r\n".getBytes("UTF-8");
        outputStream.write(CRLF);
        for (int i = 0; i < CRLF.length; i++) {
            System.out.println(Integer.toHexString(CRLF[i]));
        }
    }

}



