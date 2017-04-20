package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.io.*;
import java.util.List;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpRequest {

    private BufferedReader bufferedReader;
    private String requestLine;
    private String method;
    private String requestUri;
    private String httpVersion;
    private String requestHeader;
    private String requestMessageBody;
    private static final String FILE_PATH = "src/main/java/Document/";

    //リクエストからmethod,requestUri,httpversionを抜き出す
    public void requestLineSplit() {

        HttpServer httRequestData = new HttpServer();
        httRequestData.getList();

        method = getlist(0);
        requestUri = getList(1);
        httpVersion = getList(2);
    }

    //リクエストURIとファイルパスから呼び出すファイルを特定する
    public File getRequestDocument(String requestUri) {
        File file = new File(FILE_PATH + requestUri);//このままだとsrc/main/java/Document/+http://www.w3.org/pub/WWW/TheProject.html になってちがう
        return file;
    }

    //リクエストUriのHostぬきだすのｋｓんｓ

    //リクエストURIからファイルの拡張子を取得する　responseのcontent-typeのため
    public String getExtension(String requestUri){
        String fileExtension = new String();
        int lastDotPosition = requestUri.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return null;
    }


    public HttpRequest(List<String> lines) {

    }

}
