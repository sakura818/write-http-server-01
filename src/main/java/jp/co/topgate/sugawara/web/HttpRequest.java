package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


/**
 * HttpRequest class
 * リクエストを分解する
 *
 * @author sakura818
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


    HttpServer httpServer = new HttpServer();
    String requestData = httpServer.getRequest();// rename

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
    RequestMessageBodyの処理 getter
     */

    public String getRequestMessageBody() {
        return this.requestMessageBody;
    }

    /**
     * 改行文字を区切り文字としてリクエストを4つに分割する
     */
    public String[] reqDataDivide() throws IOException {
        String reqDelimiterDivide[] = requestData.toString().split("\\r?\\n");// mac,windows crlf　

        this.requestLine = reqDelimiterDivide[0];
        this.requestHeader = reqDelimiterDivide[1];
        this.emptyLine = reqDelimiterDivide[2];
        this.requestMessageBody = reqDelimiterDivide[3];

        return reqDelimiterDivide;
    }

    /**
     * 空白文字を区切り文字としてrequestLineを3つに分割する
     */

    public String[] requestLineDivide() {

        String reqUriDelimiterDivide[] = requestLine.split("\\s");

        this.method = reqUriDelimiterDivide[0];//　1つめの空白文字までを変数reqlineにいれる処理
        this.requestUri = reqUriDelimiterDivide[1];//　2つめの空白文字までを変数reqlineにいれる処理
        this.httpVersion = reqUriDelimiterDivide[2];//　3つめの空白文字までを変数reqlineにいれる処理
        return reqUriDelimiterDivide;
    }


    /**
     * Request-URI に "% HEX HEX" エンコードが使用されていたらデコードする
     */
    private String requestUriDecode(String requestUri) throws UnsupportedEncodingException {
        String decodeUri = URLDecoder.decode(this.requestUri, "UTF-8");
        return decodeUri;
    }

    /**
     * Request-URIからパス名を抜き出す
     */
    private String requestUriPath(String decodeUri) throws URISyntaxException {
        URI uriPath = new URI(decodeUri);
        return uriPath.getPath();
    }

    /**
     * dexodeされたRequest-URIからパス名を抜き出す
     */
    public String requestUriDecodeAndPath() throws IOException, URISyntaxException {// rename
        String decodeUri = requestUriDecode(requestUri);
        return requestUriPath(decodeUri);
    }


}
