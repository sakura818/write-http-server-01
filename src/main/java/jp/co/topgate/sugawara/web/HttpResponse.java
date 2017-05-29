package jp.co.topgate.sugawara.web;

import java.io.*;

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

    public HttpResponse(File file, int statusCode) {
        this.file = file;
        this.statusCode = statusCode;
    }

    /**
     * HttpResponseのコンテンツを組み立てる
     * HttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param file
     * @param statusCode
     * @throws IndexOutOfBoundsException, IOException, ArrayStoreException, NullPointerException
     */

    public byte[] createHttpResponseContent(File file, int statusCode) throws IOException, NullPointerException {

        HttpResponseStatusLineBuilder statusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
        HttpResponseMessageHeaderBuilder messageHeaderBuilder = new HttpResponseMessageHeaderBuilder(file);
        HttpResponseMessageBodyBuilder messageBodyBuilder = new HttpResponseMessageBodyBuilder(file);

        byte[] statusLine = statusLineBuilder.build();
        byte[] messageHeader = messageHeaderBuilder.build();
        byte[] messageBody = messageBodyBuilder.build();


        int statusLineLength = statusLine.length;
        int messageHeaderLength = messageHeader.length;
        int messageBodyLength = messageBody.length;


        byte[] createResponseContents = new byte[statusLineLength + messageHeaderLength + messageBodyLength];

        System.arraycopy(statusLine, 0, createResponseContents, 0, statusLineLength);
        System.arraycopy(messageHeader, 0, createResponseContents, statusLineLength, messageHeaderLength);
        System.arraycopy(messageBody, 0, createResponseContents, (statusLineLength + messageHeaderLength), messageBodyLength);

        return createResponseContents;
    }


    /**
     * HttpResponseのコンテンツをOutputStreamに書き込む
     * HttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param outputStream バイト出力ストリーム
     * @throws IndexOutOfBoundsException, IOException, ArrayStoreException, NullPointerException
     */

    public void writeToOutputStream(OutputStream outputStream) throws IOException, NullPointerException {

        byte[] httpResponseContent = createHttpResponseContent(file, statusCode);
        /** HttpResponseをoutputStreamに書き込む */
        outputStream.write(httpResponseContent);

    }
}