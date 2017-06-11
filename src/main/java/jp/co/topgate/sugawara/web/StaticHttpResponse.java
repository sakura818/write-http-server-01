package jp.co.topgate.sugawara.web;

import java.io.*;

/**
 * HttpResponse class
 * HttpResponseのコンテンツを組み立て、バイト出力ストリームOutputStreamに書き込む
 * HttpResponse = StatusLine + MessageHeader + MessageBody
 *
 * @author sakura818
 */

public class StaticHttpResponse {

    private File file;
    private int statusCode;

    /**
     * StaticHttpResponseのコンストラクタ
     *
     * @param file,statusCode
     */

    public StaticHttpResponse(File file, int statusCode) {
        this.file = file;
        this.statusCode = statusCode;
    }

    /**
     * StaticHttpResponseのコンテンツを組み立てる
     * StaticHttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param file
     * @param statusCode
     * @throws IOException
     */

    public byte[] createStaticHttpResponseContent(File file, int statusCode) throws IOException {

        StaticHttpResponseStatusLineBuilder staticHttpResponseStatusLineBuilder = new StaticHttpResponseStatusLineBuilder(statusCode);
        StaticHttpResponseMessageHeaderBuilder staticHttpResponseMessageHeaderBuilder = new StaticHttpResponseMessageHeaderBuilder(file);
        StaticHttpResponseMessageBodyBuilder staticHttpResponseMessageBodyBuilder = new StaticHttpResponseMessageBodyBuilder(file);

        byte[] statusLine = staticHttpResponseStatusLineBuilder.build();
        byte[] messageHeader = staticHttpResponseMessageHeaderBuilder.build();
        byte[] messageBody = staticHttpResponseMessageBodyBuilder.build();


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
     * HttpResponseをOutputStreamに書き込む
     *
     * @param outputStream バイト出力ストリーム
     * @throws IOException
     */

    public void writeToOutputStream(OutputStream outputStream) throws IOException {

        byte[] httpResponseContent = createStaticHttpResponseContent(file, statusCode);
        /** HttpResponseをoutputStreamに書き込む */
        outputStream.write(httpResponseContent);

    }
}