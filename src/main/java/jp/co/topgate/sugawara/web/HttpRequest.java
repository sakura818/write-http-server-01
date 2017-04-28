package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URLDecoder;
import java.net.URI;
import java.io.File;


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
    private String emptyLine;
    private String requestMessageBody;
    private String decodeUri;
    private String pathUri;
    private static final String FILE_DIR = "src/main/java/resources/";

    HttpServer httpServer = new HttpServer();
    BufferedReader reqdata = httpServer.getReqdata();// rename


    /*
    改行文字を区切り文字としてリクエストを分割する
     */

    public String[] reqDataDivide() throws IOException {
        reqdata.readLine();// 文字ストリームから改行終端文字を読み取るのがreadLineメソッド
        String reqDelimiterDivide[] = reqdata.toString().split("\\r?\\n");// mac,windows crlf　
        //reqDelimiterDivide= new String[];

        this.requestLine = reqDelimiterDivide[0];//　1つめの改行終端文字までを変数reqheaderにいれる処理
        this.requestHeader = reqDelimiterDivide[1];//　2つめの改行終端文字までを変数reqheaderにいれる処理
        this.emptyLine = reqDelimiterDivide[2];//　3つめの改行終端文字までを変数emptylineにいれる処理
        this.requestMessageBody = reqDelimiterDivide[3];//　4つめの改行終端文字までを変数reqmessagebodyにいれる処理

        return reqDelimiterDivide;
    }

    /*
    空白文字を区切り文字としてリクエストuriを分割する
     */

    public String[] requestLineDivide() {
        String reqUriDelimiterDivide[] = requestLine.split("\\s");

        this.method = reqUriDelimiterDivide[0];//　1つめの空白文字までを変数reqlineにいれる処理
        this.requestUri = reqUriDelimiterDivide[1];//　2つめの空白文字までを変数reqlineにいれる処理
        this.httpVersion = reqUriDelimiterDivide[2];//　3つめの空白文字までを変数reqlineにいれる処理
        return reqUriDelimiterDivide;

    }

    /*
    methodの処理 getter
     */

    public String getMethod() {
        return this.method;
    }

    /*
    requestUriの処理 getter
     */

    public String getRequestUri() {
        return this.requestUri;
    }

    /*
    HTTPVersionの処理 getter
     */

    public String getHttpVersion() {
        return this.httpVersion;
    }

    /*
    requestHeaderの処理 getter
     */

    public String getRequestHeader() {
        return this.requestHeader;
    }

    /*
    EmptyLineの処理 getter
     */

    public String getEmptyLine() {
        return this.emptyLine;
    }

    /*
    RequestMessageBodyの処理 getter
     */

    public String getRequestMessageBody() {
        return this.requestMessageBody;
    }

    /*
    UriDecodeの処理 getter
     */

    public String getDecodeUri() {
        return this.decodeUri;
    }



    /*
    もし、Request-URI に "% HEX HEX" エンコード [42] が使用されていたら、オリジンサーバはそのリクエストを適切に解釈するためにその
    Request-URI をデコードし*なければならない*。
     */
    public String requestUriDecode() throws UnsupportedEncodingException{
        String decodeUri = URLDecoder.decode(this.requestUri, "UTF-8");
        return decodeUri;
    }

    /*
    Request-URIからパス名を抜き出す
     */
    public String requestUriPath() {
        //URI u = new URI(this.decodeUri);
        String pathUri = decodeUri.getPath();//?
        return pathUri;
    }
    

    //リクエストURIから拡張子を取得する　
    //lastIndexOfでnullが来た場合の処理ができていない　nullPointerExceptionになる
    public String convertPathUriToExtension(String requestUri) {
        //String fileExtension = new String();
        int lastDotPosition = this.pathUri.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return this.requestUri.substring(lastDotPosition + 1);
        } else {
            return "拡張子を取得できませんでした";
        }

    }
}
