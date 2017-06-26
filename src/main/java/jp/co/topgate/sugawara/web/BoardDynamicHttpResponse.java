package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponse extends DynamicHttpResponse {

    private File file;
    private int statusCode;
    private String dynamicHttpResponseAssort;
    private HttpRequest httpRequest;
    private Map<String, String> responseMessageBody;
    private String rawPassword;
    private String queryString;

    /**
     * コンストラクタ
     *
     * @param file,statusCode
     */

    public BoardDynamicHttpResponse(File file, int statusCode, String responseAssort, String rawPassword, Map<String, String> responseMessageBody, String queryString) throws IOException { // TODO:引数
        this.file = file;
        this.statusCode = statusCode;
        this.dynamicHttpResponseAssort = responseAssort;
        this.responseMessageBody = responseMessageBody;
        this.rawPassword = rawPassword;
        this.queryString = queryString;
    }

    /**
     * BoardDynamicHttpResponse
     * BoardDynamicHttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param file
     * @param statusCode
     * @throws IOException
     */

    public byte[] createDynamicHttpResponseContent(File file, int statusCode, HttpRequest httpRequest, Map<String, String> responseMessageBody, String rawPassword) throws IOException {// TODO:引数

        BoardDynamicHttpResponseStatusLineBuilder boardDynamicHttpResponseStatusLineBuilder = new BoardDynamicHttpResponseStatusLineBuilder(statusCode);
        BoardDynamicHttpResponseMessageHeaderBuilder boardDynamicHttpResponseMessageHeaderBuilder = new BoardDynamicHttpResponseMessageHeaderBuilder(file);
        BoardDynamicHttpResponseMessageBodyBuilder boardDynamicHttpResponseMessageBodyBuilder = new BoardDynamicHttpResponseMessageBodyBuilder(dynamicHttpResponseAssort, queryString, responseMessageBody, rawPassword, httpRequest);// TODO:引数

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

    public void writeToOutputStream(File file, int statusCode, HttpRequest httpRequest, OutputStream outputStream, Map<String, String> responseMessageBody) throws IOException {// TODO:引数

        byte[] httpResponseContent = createDynamicHttpResponseContent(file, statusCode, httpRequest, responseMessageBody, rawPassword);// TODO:引数
        outputStream.write(httpResponseContent);

    }
}
