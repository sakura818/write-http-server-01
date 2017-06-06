package jp.co.topgate.sugawara.web;


import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


/**
 * HttpRequest class
 * inputStreamからHttpRequestを読み込む処理を行う
 * HttpRequest = RequestLine + MessageHeader + MessageBody
 * 今回の課題では簡易的な機能しか提供しないためMessageHeaderとMessageBodyは読み込んでいない
 *
 * @author sakura818
 */

public class HttpRequest {
    private String uriPath;
    private String requestUri;
    private int statusCode;
    private String queryString;
    private final int OK = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_IMPLEMENTED = 501;
    private final int HTTP_VERSION_NOT_SUPPORTED = 505;


    /**
     * HttpRequestのコンストラクタ
     *
     * @param inputStream
     */

    public HttpRequest(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        String requestLine = bufferedReader.readLine();
        int statusCode = judgeStatusCode(requestLine);
        this.statusCode = statusCode;
        if (statusCode == OK) {
            String requestUri = parseRequestUri(requestLine);
            this.requestUri = requestUri;

            String uriPath = parseUriPath(requestUri);
            if (uriPath.matches(".*\\?.*")) {
                String[] uriPathAndQueryString = divideUriPathAndQueryString(uriPath);
                uriPath = uriPathAndQueryString[0];
                queryString = uriPathAndQueryString[1];
            }
            this.uriPath = uriPath;
            this.queryString = queryString;
        }
    }

    /**
     * requestLineからstatusCodeをjudgeする
     * requestLine = method + requestUri + httpVersion
     *
     * @param requestLine ex:GET /index.html HTTP/1.1
     * @return statusCode ex:200
     */

    int judgeStatusCode(String requestLine) {
        if (requestLine != null) {
            String[] requestLineArray = requestLine.split(" ", 3);
            if ((requestLineArray.length == 3) && (implementedHttpMethod.contains(requestLineArray[0]) == true) && (supportedHttpVersions.contains(requestLineArray[2]) == true)) {
                statusCode = OK;
            } else if ((requestLineArray.length == 3) && (notImplementedHttpMethod.contains(requestLineArray[0]) == true)) {
                statusCode = NOT_IMPLEMENTED;
            } else if ((requestLineArray.length == 3) && (notSupportedHttpVersions.contains(requestLineArray[2]) == true)) {
                statusCode = HTTP_VERSION_NOT_SUPPORTED;
            } else {
                statusCode = BAD_REQUEST;
            }
        }
        return statusCode;
    }

    /**
     * このHttpServerで実装しておりRFC2616に記載されているmethodのリスト
     */

    List<String> implementedHttpMethod = new ArrayList<String>() {
        {
            add("GET");
        }
    };

    /**
     * このHttpServerで実装していないがRFC2616に記載されているmethodのリスト
     */

    List<String> notImplementedHttpMethod = new ArrayList<String>() {
        {
            add("HEAD");
            add("POST");
            add("PUT");
            add("DELETE");
            add("TRACE");
            add("HEAD");
            add("CONNECT");
        }
    };

    /**
     * このHttpServerでサポートしておりRFC2616に記載されているhttpVersionのリスト
     */

    List<String> supportedHttpVersions = new ArrayList<String>() {
        {
            add("HTTP/1.1");
        }
    };

    /**
     * このHttpServerでサポートしていないがRFC2616に記載されているhttpVersionのリスト
     */

    List<String> notSupportedHttpVersions = new ArrayList<String>() {
        {
            add("HTTP/0.9");
            add("HTTP/1.0");
            add("HTTP/2");
        }
    };

    /**
     * requestLineからrequestUriをparseする
     * requestLine = method + requestUri + httpVersion
     *
     * @param requestLine ex:GET /index.html HTTP/1.1
     * @return requestUri ex:index.html
     */

    String parseRequestUri(String requestLine) throws UnsupportedEncodingException {
        if (requestLine != null) {
            String[] requestLineArray = requestLine.split(" ", 3);
            String decodedRequestUri = URLDecoder.decode(requestLineArray[1], "UTF-8");
            requestUri = decodedRequestUri;
            if (requestUri.endsWith("/")) {
                requestUri += "index.html";
            }
        }
        return requestUri;
    }

    /**
     * requestUriからUriPathを抜き出す
     *
     * @param requestUri ex:http://localhost:8080/index.html
     * @return UriPath ex:/index.html
     */

    String parseUriPath(String requestUri) {
        String host = "http://localhost:8080";
        if (requestUri != null) {
            if (requestUri.startsWith(host)) {
                requestUri = requestUri.replace(host, "");
            }
            uriPath = requestUri;
        }
        return uriPath;
    }

    /**
     * uriPathにクエストリングがあったときuriPathとクエストリングを分ける
     *
     * @param uriPath /index.html?id=1&name=hana
     * @return queryString
     */
    public String[] divideUriPathAndQueryString(String uriPath) throws UnsupportedEncodingException {
        String uriPathAndQueryString[] = new String[2];
        if (uriPath != null) {
            uriPathAndQueryString = uriPath.split("\\?", 2);
        }
        return uriPathAndQueryString;
    }


    /**
     * statusCodeを取得する
     *
     * @return statusCode
     */

    public int getStatusCode() {
        return this.statusCode;
    }


    /**
     * uriPathを取得する
     *
     * @return uriPath
     */

    public String getUriPath() {
        return this.uriPath;
    }


    /**
     * テストのためにrequestUriを取得する
     *
     * @return requestUri
     */

    public String getRequestUri() {
        return this.requestUri;
    }


}



