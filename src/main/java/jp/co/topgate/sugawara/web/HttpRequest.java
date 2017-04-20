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
        //requestLineSplit = getList(0);//getlistで1行目を呼び出して空白を基点に3つに分割したい
        //requestLineSplit = getList().lines(0);//getlistの呼び出しができてないのかも　メソッドの呼び出し方は"メソッド名（引数リスト）"
        //requestLineSplit = getList(lines).(0);
        //String[] requestLineSplit = (String[]) getlist.toArray(new String[0]);//listを配列に一旦変換させてからやってみる→失敗

        String[] requestLine = requestLineSplit(" ");

        method = requestLine[0];
        requestUri = requestLine[1];
        httpVersion = requestLine[2];
    }

    //リクエストURIとファイルパスから呼び出すファイルを特定する→意図は?　
    public File getRequestDocument(String requestUri) {
        File file = new File(FILE_PATH + requestUri);//このままだとsrc/main/java/Document/+http://www.w3.org/pub/WWW/TheProject.html になってちがう
        return file;
    }

    //リクエストUriのHostぬきだす


    //リクエストURIからファイルの拡張子を取得する　responseのcontent-typeのため
    public String getExtension(String requestUri) {
        String fileExtension = new String();
        int lastDotPosition = requestUri.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return null;
    }

}
