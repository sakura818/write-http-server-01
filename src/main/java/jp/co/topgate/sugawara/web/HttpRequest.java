package jp.co.topgate.sugawara.web;


import java.io.IOException;
import java.lang.String;
import java.io.*;
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

            String UriPath = parseUriPath(requestUri);
            this.uriPath = UriPath;
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
            if ((requestLineArray.length == 3) && (availableMethod.contains(requestLineArray[0]) == true) && (availableHttpVersion.contains(requestLineArray[2]) == true)) {
                statusCode = OK;
            } else if ((requestLineArray.length == 3) && (notAvailableMethod.contains(requestLineArray[0]) == true)) {
                statusCode = NOT_IMPLEMENTED;
            } else if ((requestLineArray.length == 3) && (notAvailableHttpVersion.contains(requestLineArray[2]) == true)) {
                statusCode = HTTP_VERSION_NOT_SUPPORTED;
            } else {
                statusCode = BAD_REQUEST;
            }
        }
        return statusCode;
    }


    /**
     * requestLineからUriPathをparseする
     * requestLine = method + requestUri + httpVersion
     *
     * @param requestLine ex:GET /index.html HTTP/1.1
     * @return requestUri ex:index.html
     */

    String parseRequestUri(String requestLine) {
        if (requestLine != null) {
            String[] requestLineArray = requestLine.split(" ", 3);
            requestUri = requestLineArray[1];
            if (requestUri.equals("/")) {
                requestUri += "index.html";
            }
        }
        return requestUri;
    }

    /**
     * このHttpServerでサポートしているmethodのリスト
     */

    List<String> availableMethod = new ArrayList<String>() {
        {
            add("GET");
        }
    };

    /**
     * このHttpServerでサポートしていないmethodのリスト
     */

    List<String> notAvailableMethod = new ArrayList<String>() {
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
     * このHttpServerでサポートしているhttpVersionのリスト
     */

    List<String> availableHttpVersion = new ArrayList<String>() {
        {
            add("HTTP/1.1");
        }
    };

    /**
     * このHttpServerでサポートしていないhttpVersionのリスト
     */

    List<String> notAvailableHttpVersion = new ArrayList<String>() {
        {
            add("HTTP/0.9");
            add("HTTP/1.0");
            add("HTTP/2");
        }
    };


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
     * テストのためにuriPathを取得する
     *
     * @return requestUri
     */

    public String getRequestUri() {
        return this.requestUri;
    }


}



