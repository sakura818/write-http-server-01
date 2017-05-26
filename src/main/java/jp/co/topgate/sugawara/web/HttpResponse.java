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

    public byte[] createHttpResponseContents(File file, int statusCode) throws Exception {

        HttpResponseStatusLineBuilder statusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
        HttpResponseMessageHeaderBuilder messageHeaderContent = new HttpResponseMessageHeaderBuilder(file);
        HttpResponseMessageBodyBuilder messageBodyBuilder = new HttpResponseMessageBodyBuilder(file);

        byte[] CRLF = "\r\n".getBytes("UTF-8");
        byte[] createResponseContents = new byte[(statusLineBuilder.build()).length + (messageHeaderContent.build()).length + (messageBodyBuilder.build()).length + CRLF.length];

        System.arraycopy(statusLineBuilder.build(), 0, createResponseContents, 0, (statusLineBuilder.build()).length);
        System.arraycopy((messageHeaderContent.build()), 0, createResponseContents, (statusLineBuilder.build()).length, (messageHeaderContent.build()).length);
        System.arraycopy((messageBodyBuilder.build()), 0, createResponseContents, ((statusLineBuilder.build().length) + (messageHeaderContent.build()).length), (messageBodyBuilder.build()).length);
        System.arraycopy(CRLF, 0, createResponseContents, ((statusLineBuilder.build().length) + (messageHeaderContent.build()).length + (messageBodyBuilder.build()).length), CRLF.length);

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
        outputStream.write(createHttpResponseContents(file, statusCode));

        System.out.println("http response...");
        /** HttpResponseをコンソールに表示する */
        for (int i = 0; i < createHttpResponseContents(file, statusCode).length; i++) {
            System.out.print(createHttpResponseContents(file, statusCode)[i]);
        }
        System.out.println("flag");
        System.out.println(createHttpResponseContents(file, statusCode)[(createHttpResponseContents(file, statusCode).length) -2]);
        System.out.println(createHttpResponseContents(file, statusCode)[(createHttpResponseContents(file, statusCode).length) -1]);
    }
}