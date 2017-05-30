package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;


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

    /**
     * HttpRequestのコンストラクタ
     *
     * @param inputStream
     */

    public HttpRequest(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        String requestLine = bufferedReader.readLine();
        System.out.println(requestLine);

        String requestUri = parseRequestUri(requestLine);
        this.requestUri = requestUri;

        String UriPath = parseUriPath(requestUri);
        this.uriPath = UriPath;
    }


    /**
     * requestLineからrequestUriをgetする
     * requestLine = method + requestUri + httpVersion
     * requestLine ex:GET http://localhost:8080/index.html HTTP/1.1
     *
     * @param requestLine
     * @return UriPath ex:index.html
     */

    String parseRequestUri(String requestLine) {
        if (requestLine != null) {
            String[] requestLineArray = requestLine.split(" ", 3);
            if ((requestLineArray.length == 3) && ("GET".equals(requestLineArray[0])) && ("HTTP/1.1".equals(requestLineArray[2]))) {
                requestUri = requestLineArray[1];
                if (requestUri.equals("/")) {
                    requestUri += "index.html";
                }
            }
        }
        return requestUri;
    }

    /**
     * requestUriからUriPathを抜き出す
     *
     * @param requestUri ex:http://localhost:8080/index.html
     * @return UriPath ex:index.html
     */

    String parseUriPath(String requestUri) {
        String host = "http://localhost:8080";
        if(requestUri.startsWith(host)){
            requestUri = requestUri.replace(host,"");
        }
        uriPath = requestUri;
        return uriPath;
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



