package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URLDecoder;


/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpRequest {

    private BufferedReader bufferedReader;
    //private String requestLine;
    private String method;
    private String requestUri;
    private String httpVersion;
    private String requestHeader;
    private String emptyLine;
    private String requestMessageBody;
    private static final String FILE_DIR = "src/main/java/resources/";

    HttpServer httpServer = new HttpServer();
    BufferedReader reqdata = httpServer.getReqdata();// rename


    /*
    空白文字または改行文字を区切り文字としてリクエストを分割する
     */

    public String[] reqDataDivide() throws IOException {
        reqdata.readLine();// 文字ストリームから改行終端文字を読み取るのがreadLineメソッド
        String reqDelimiterDivide[] = reqdata.toString().split("\\r?\\n|\\s");// mac,windows crlf
        //reqDelimiterDivide= new String[];

        this.method = reqDelimiterDivide[0];//　1つめの空白文字までを変数reqlineにいれる処理
        this.requestUri = reqDelimiterDivide[1];//　2つめの空白文字までを変数reqlineにいれる処理
        this.httpVersion = reqDelimiterDivide[2];//　3つめの空白文字までを変数reqlineにいれる処理
        this.requestHeader = reqDelimiterDivide[3];//　2つめの改行終端文字までを変数reqheaderにいれる処理
        this.emptyLine = reqDelimiterDivide[4];//　3つめの改行終端文字までを変数emptylineにいれる処理
        this.requestMessageBody = reqDelimiterDivide[5];//　4つめの改行終端文字までを変数reqmessagebodyにいれる処理

        return reqDelimiterDivide;
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
    もし、Request-URI に "% HEX HEX" エンコード [42] が使用されていたら、オリジンサーバはそのリクエストを適切に解釈するためにその
    Request-URI をデコードし*なければならない*。
     */
    public String requestUriDecode() throws UnsupportedEncodingException{
        String decodeUri = URLDecoder.decode(this.requestUri, "UTF-8");
        return decodeUri;
    }


    //リクエストURIとファイルパスから呼び出すファイルを特定する responseのfileExistCheckのため
    //理想はhttp://localhost:8080/hello.html からsrc/main/java/Document/hello.htmlをよびだすこと
    //いまは上の階層の処理ができてないかつ?が来た場合の処理ができてない
    //URIの特別なクラスがあるらしい（独習Java）→https://docs.oracle.com/javase/jp/8/docs/api/java/net/URI.html
    public String getRequestFile(String requestUri) {

        String file = new String();
        String fileName = null;
        int lastSlashPosition = this.requestUri.lastIndexOf("/");
        if (lastSlashPosition != -1) {
            fileName = FILE_DIR + this.requestUri.substring(lastSlashPosition + 1);
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
            return "拡張子を取得できませんでした";
        }

    }
}
