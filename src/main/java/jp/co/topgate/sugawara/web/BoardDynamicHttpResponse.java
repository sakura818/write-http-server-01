package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponse extends DynamicHttpResponse {

    private File file;
    private int statusCode;
    private String assort;
    private HttpRequest httpRequest;

    /**
     * コンストラクタ
     *
     * @param file,statusCode
     */

    public BoardDynamicHttpResponse(File file, int statusCode, HttpRequest httpRequest, BoardDynamicHttpResponseHandler boardDynamicHttpResponseHandler, InputStream inputStream) {
        this.file = file;
        this.statusCode = statusCode;
        this.assort = boardDynamicHttpResponseHandler.dynamicHttpResponseAssort(httpRequest);
        System.out.println(assort);
        this.httpRequest = httpRequest;
    }

    /**
     * BoardDynamicHttpResponse
     * BoardDynamicHttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param file
     * @param statusCode
     * @throws IOException
     */

    public byte[] createDynamicHttpResponseContent(File file, int statusCode, HttpRequest httpRequest, InputStream inputStream) throws IOException {

        BoardDynamicHttpResponseStatusLineBuilder boardDynamicHttpResponseStatusLineBuilder = new BoardDynamicHttpResponseStatusLineBuilder(statusCode);
        BoardDynamicHttpResponseMessageHeaderBuilder boardDynamicHttpResponseMessageHeaderBuilder = new BoardDynamicHttpResponseMessageHeaderBuilder(file);
        BoardDynamicHttpResponseMessageBodyBuilder boardDynamicHttpResponseMessageBodyBuilder = new BoardDynamicHttpResponseMessageBodyBuilder(file, assort, httpRequest, inputStream);

        byte[] statusLine = boardDynamicHttpResponseStatusLineBuilder.build();
        byte[] messageHeader = boardDynamicHttpResponseMessageHeaderBuilder.build();
        byte[] messageBody = boardDynamicHttpResponseMessageBodyBuilder.build();

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

    public void writeToOutputStream(File file, int statusCode, HttpRequest httpRequest, InputStream inputStream, OutputStream outputStream) throws IOException {

        byte[] httpResponseContent = createDynamicHttpResponseContent(file, statusCode, httpRequest, inputStream);
        /** HttpResponseをoutputStreamに書き込む */
        outputStream.write(httpResponseContent);

    }
}
