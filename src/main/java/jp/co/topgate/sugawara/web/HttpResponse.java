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


    private String statusLine;
    private String extension;

    public String getStatusLine() {
        return this.statusLine;
    }

    public void setResponseMessageBodyFile(File file) {
        this.responseMessageBodyFile = file;
    }

    /**
     * レスポンスをクライアントに送信する
     */
    public void sendHttpResponse(createHttpResponseContentの戻り値) {
        PrintWriter writer = new PrintWriter(outputStream, true);

    }


}
