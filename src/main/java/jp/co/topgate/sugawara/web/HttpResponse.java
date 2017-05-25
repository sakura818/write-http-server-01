package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.ArrayList;

/**
 * HttpResponse class
 * HttpResponseのコンテンツをバイト出力ストリームOutputStreamに書き込む
 * HttpResponse = StatusLine + MessageHeader + MessageBody
 *
 * @author sakura818
 */

public class HttpResponse {

    private File file;
    private int statusCode;

    public HttpResponse(OutputStream outputStream, File file, int statusCode) {
        this.file = file;
        this.statusCode = statusCode;
    }

    /**
     * HttpResponseのコンテンツを組み立てる
     * HttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param file
     * @param statusCode
     * @throws Exception
     */

    public ArrayList<byte[]> createHttpResponseContents(File file, int statusCode) throws Exception {

        ArrayList<byte[]> createResponseContents = new ArrayList<byte[]>();
        {
            /** HttpResponseのStatusLineをバイト出力ストリームに書き込む */
            HttpResponseStatusLineBuilder statusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
            /** HttpResponseのMessageHeaderをバイト出力ストリームに書き込む */
            HttpResponseMessageHeaderBuilder messageHeaderContent = new HttpResponseMessageHeaderBuilder(file);
            /** HttpResponseのMessageBodyをバイト出力ストリームに書き込む */
            HttpResponseMessageBodyBuilder messageBodyBuilder = new HttpResponseMessageBodyBuilder(file);
            /** HttpResponseのMessageBodyの最後のflagとなるCRLFをバイト出力ストリームに書き込む OutputStreamクラスのwriteメソッドでは最後改行がされないため*/
            byte[] CRLF = "\r\n".getBytes("UTF-8");

            createResponseContents.add(statusLineBuilder.build());
            createResponseContents.add(messageHeaderContent.build());
            createResponseContents.add(messageBodyBuilder.build());
            createResponseContents.add(CRLF);
        }
        return createResponseContents;
    }


    /**
     * HttpResponseのコンテンツをOutputStreamに書き込む
     * HttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param outputStream バイト出力ストリーム
     * @throws IOException
     */

    public void writeToOutputStream(OutputStream outputStream) throws Exception {
        /** HttpResponseをoutputStreamに書き込む */
        createHttpResponseContents(file, statusCode);
        for (int i = 0; i < (createHttpResponseContents(file, statusCode)).size(); i++) {
            outputStream.write(createHttpResponseContents(file, statusCode).get(i));   //
        }

        /** HttpResponseをコンソールに表示する */
        System.out.println("http response...");
        for (int i = 0; i < (createHttpResponseContents(file, statusCode)).size(); i++) {
            System.out.println((createHttpResponseContents(file, statusCode).get(i)));
        }

    }
}



