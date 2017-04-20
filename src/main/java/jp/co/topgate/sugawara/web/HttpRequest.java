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

    public HttpRequest() {

        String[] requestLine = HttpServer.getList().get(0).split(" ");

        method = requestLine[0];
        requestUri = requestLine[1];
        httpVersion = requestLine[2];
    }


    /*
    public List<String> getList(){


        return this.lines;
    }
    */

    //リクエストからmethod,requestUri,httpversionを抜き出す
    public void requestLineSplit() {

//        method = requestLine[0];
//        requestUri = requestLine[1];
//        httpVersion = requestLine[2];
    }

    //リクエストURIとファイルパスから呼び出すファイルを特定する→意図は?　
    public File getRequestDocument(String requestUri) {
        File file = new File(FILE_PATH + requestUri);
        //このままだとsrc/main/java/Document/+http://localhost:8080/hello.html になってちがう
        //理想はhttp://localhost:8080/hello.html から
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
