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
    public HttpRequest() {//rename

        String[] requestLine = lines.getList().get(0).split(" ");

        this.method = requestLine[0];
        this.requestUri = requestLine[1];
        this.httpVersion = requestLine[2];
    }

    //リクエストURIとファイルパスから呼び出すファイルを特定する responseのfileExistCheckのため
    //理想はhttp://localhost:8080/hello.html からsrc/main/java/Document/hello.htmlをよびだすこと
    //いまは上の階層の処理ができてないかつ?が来た場合の処理ができてない
    public String getRequestFile(String requestUri) {

        String file = new String();
        String fileName = null;
        int lastSlashPosition = this.requestUri.lastIndexOf("/");
        if (lastSlashPosition != -1) {
            fileName = FILE_PATH + this.requestUri.substring(lastSlashPosition + 1);
        } else {
            fileName = null;
        }
        return fileName;
    }

    //リクエストURIから拡張子を取得する　
    //lastIndexOfでnullが来た場合の処理ができていない　nullPointerExceptionになる
    public String convertRequestUriToExtension(String requestUri) {
        String fileExtension = new String();
        int lastDotPosition = this.requestUri.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return this.requestUri.substring(lastDotPosition + 1);
        } else {
            return null;
        }

    }
}
