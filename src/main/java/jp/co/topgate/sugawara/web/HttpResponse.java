package jp.co.topgate.sugawara.web;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HttpResponse class
 * レスポンスのクライアントにまつわる部分をとり扱う
 * TODO:ファイルの読み込みがわかってない
 *
 * @author sakura818
 */
public class HttpResponse {
    private File responseMessageBodyFile;
    BufferedInputStream bis = null;
    StatusCode statusCode = new StatusCode();

    private String statusLine;
    private String extension;

    public String getStatusLine() {
        return this.statusLine;
    }
    public void setResponseMessageBodyFile(File file) {
        this.responseMessageBodyFile = file;
    }

    /**
     * レスポンスの部品を集めて組み立て生成
     */
    public void createHttpResponse(OutputStream outputStream, int currentStatusCode) {
        // PrintWriter writer = new PrintWriter(outputStream, true);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + statusCode.mappingStatusCode(currentStatusCode)).append("\n");
        sb.append(createResponseMessageHeader()).append("\n");
        sb.append(createResponseMessageBody(statusCode)).append("\n");

    }

}

