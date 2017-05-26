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

    public byte[] createHttpResponseContent(File file, int statusCode) throws Exception {

        HttpResponseStatusLineBuilder statusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
        HttpResponseMessageHeaderBuilder messageHeaderBuilder = new HttpResponseMessageHeaderBuilder(file);
        HttpResponseMessageBodyBuilder messageBodyBuilder = new HttpResponseMessageBodyBuilder(file);

        byte[] CRLF = "\r\n".getBytes("UTF-8");


        int statusLineLength = (statusLineBuilder.build()).length;
        int messageHeaderLength = (messageHeaderBuilder.build()).length;
        int messageBodyLength = (messageBodyBuilder.build()).length;
        int CRLFLength = CRLF.length;

        byte[] createResponseContents = new byte[statusLineLength + messageHeaderLength + messageBodyLength + CRLFLength];

        System.arraycopy(statusLineBuilder.build(), 0, createResponseContents, 0, statusLineLength);
        System.arraycopy((messageHeaderBuilder.build()), 0, createResponseContents, statusLineLength, messageHeaderLength);
        System.arraycopy((messageBodyBuilder.build()), 0, createResponseContents, (statusLineLength + messageHeaderLength), messageBodyLength);
        System.arraycopy(CRLF, 0, createResponseContents, (statusLineLength+ messageHeaderLength + messageBodyLength), CRLFLength);

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

        byte[] httpResponseContent = createHttpResponseContent(file, statusCode);
        /** HttpResponseをoutputStreamに書き込む */
        outputStream.write(httpResponseContent);

        System.out.println("http response...");
        /** HttpResponseをコンソールに表示する */
        for (int i = 0; i < httpResponseContent.length; i++) {
            System.out.print(httpResponseContent[i]);
        }
        System.out.println("flag");
        System.out.println(httpResponseContent[(httpResponseContent.length) -2]);
        System.out.println(httpResponseContent[(httpResponseContent.length) -1]);
    }
}