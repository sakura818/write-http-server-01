package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;

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
    public HttpRequest() {

        String[] requestLine = HttpServer.getList().get(0).split(" ");

        method = requestLine[0];
        requestUri = requestLine[1];
        httpVersion = requestLine[2];
    }
    
    //リクエストURIとファイルパスから呼び出すファイルを特定する responseのfileExistCheckのため
    //理想はhttp://localhost:8080/hello.html からsrc/main/java/Document/hello.htmlをよびだすこと
    public String getRequestFile(String requestUri) {

        String file = new String();
        int lastSlashPosition = requestUri.lastIndexOf("/");
        if (lastSlashPosition != -1) {
            return FILE_PATH + requestUri.substring(lastSlashPosition + 1);
        } else {
            return null;
        }
    }

    //リクエストURIからファイルの拡張子を取得する　responseのcontent-typeのため
    public String getExtension(String requestUri) {
        String fileExtension = new String();
        int lastDotPosition = requestUri.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        } else {
            return null;
        }

    }
}
