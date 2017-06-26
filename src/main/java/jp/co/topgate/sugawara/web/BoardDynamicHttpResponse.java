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
    private Map<String, String> responseMessageBody;
    private String rawPassword;
    private String queryString;

    /**
     * コンストラクタ
     *
     * @param file,statusCode
     */

    public BoardDynamicHttpResponse(File file, int statusCode, String responseAssort, String rawPassword, Map<String, String> responseMessageBody, String queryString) throws IOException {
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
     * @throws IOException
     */

    public byte[] createDynamicHttpResponseContent() throws IOException {

        BoardDynamicHttpResponseStatusLineBuilder boardDynamicHttpResponseStatusLineBuilder = new BoardDynamicHttpResponseStatusLineBuilder(statusCode);
        BoardDynamicHttpResponseMessageHeaderBuilder boardDynamicHttpResponseMessageHeaderBuilder = new BoardDynamicHttpResponseMessageHeaderBuilder(file);
        BoardDynamicHttpResponseMessageBodyBuilder boardDynamicHttpResponseMessageBodyBuilder = new BoardDynamicHttpResponseMessageBodyBuilder(dynamicHttpResponseAssort, queryString, responseMessageBody, rawPassword);

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

    public void writeToOutputStream(OutputStream outputStream) throws IOException {// TODO:引数

        byte[] httpResponseContent = createDynamicHttpResponseContent();// TODO:引数
        outputStream.write(httpResponseContent);

    }
}
