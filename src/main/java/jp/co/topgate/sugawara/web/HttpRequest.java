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

    public HttpRequest(String tekito){

    }


    String setHTTPRequest(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.requestLine = bufferedReader.readLine();
    }

    //リクエストからmethod,requestUri,httpversionを抜き出す
    public void requestLineSplit() {

        HttpServer httprequestdata = new HttpServer();
        httprequestdata.getList();

        method = getList();
        requestUri = getList();
        httpVersion = getList().;
    }

    //リクエストURIとファイルパスから呼び出すファイルを特定する
    public File getRequestDocument(String requestUri) {
        File file = new File(FILE_PATH + requestUri);
        return file;
    }

    //リクエストURIからファイルの拡張子を取得する
    //public String getExtension{
    //}


    public HttpRequest(List<String> lines) {

    }

}
