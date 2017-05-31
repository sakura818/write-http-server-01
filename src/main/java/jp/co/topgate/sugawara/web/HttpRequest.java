package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.util.ArrayList;


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

    /**
     * HttpRequestのコンストラクタ
     *
     * @param inputStream
     */

    public HttpRequest(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        String requestLine = bufferedReader.readLine();

        this.statusCode = judgeStatusCode(requestLine);

        String requestUri = parseRequestUri(requestLine, statusCode);
        this.requestUri = requestUri;

        String UriPath = parseUriPath(requestUri);
        this.uriPath = UriPath;
    }


    int judgeStatusCode(String requestLine) {
        if (requestLine != null) {
            String[] requestLineArray = requestLine.split(" ", 3);
            if ((requestLineArray.length == 3) && (availableMethod.contains(requestLineArray[0]) == true) && (availableHttpVersion.contains(requestLineArray[2]) == true)) {
                statusCode = 200;
            } else if ((requestLineArray.length == 3) && (notAvailableMethod.contains(requestLineArray[0]) == true) || (notAvailableHttpVersion.contains(requestLineArray[2]) == true)) {
                statusCode = 500;
            } else {
                statusCode = 400;
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

    String parseRequestUri(String requestLine, int statusCode) {
        if (statusCode == 200) {
            String[] requestLineArray = requestLine.split(" ", 3);
            requestUri = requestLineArray[1];
            if (requestUri.equals("/")) {
                requestUri += "index.html";
            }
        } else if (statusCode == 500) {
            String[] requestLineArray = requestLine.split(" ", 3);
            requestUri = requestLineArray[1];
        } else if (statusCode == 400) {
            requestUri = requestLine;
        }
        return requestUri;
    }

    /**
     * このHttpServerでサポートしているmethodのリスト
     */

    ArrayList<String> availableMethod = new ArrayList<String>() {
        {
            add("GET");
        }
    };

    /**
     * このHttpServerでサポートしていないmethodのリスト
     */

    ArrayList<String> notAvailableMethod = new ArrayList<String>() {
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

    ArrayList<String> availableHttpVersion = new ArrayList<String>() {
        {
            add("HTTP/1.1");
        }
    };

    /**
     * このHttpServerでサポートしていないhttpVersionのリスト
     */

    ArrayList<String> notAvailableHttpVersion = new ArrayList<String>() {
        {
            add("HTTP/0.9");
            add("HTTP/1.0");
            add("HTTP/2");
        }
    };


    /**
     * requestUriからUriPathを抜き出す
     *
     * @param requestUri ex:/index.html
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
     * statusCode(200か500)を取得する
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


}



