package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;

/**
 * HttpRequest class
 * inputStreamからHttpRequestを読み込む処理を行う
 * HttpRequest = Request-Line + MessageHeader + MessageBody
 * 今回の課題では簡易的な機能しか提供しないためMessageHeaderとMessageBodyは読み込んでいない
 *
 * @author sakura818
 */

public class HttpRequest {
    private String UriPath;
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
        String requestUri = getRequestUri(requestLine);

        String UriPath = parseFilePath(requestUri);
        if (UriPath.endsWith("/")) {
            UriPath += "index.html";
        }
        this.UriPath = UriPath;
    }


    /**
     * requestLineからrequestUriをgetする
     * requestLine = method + requestUri + httpVersion
     * requestLine ex:GET http://localhost:8080/index.html HTTP/1.1
     *
     * @param requestLine
     * @return UriPath ex:index.html
     */

    String getRequestUri(String requestLine) {
        String[] requestLineArray = requestLine.split(" ", 3);

        if ((requestLineArray.length == 3)) {
            requestUri = requestLineArray[1];
        }

        //requestUri = requestLineArray[1];
        return requestUri;
    }

    /**
     * requestUriからUriPathを抜き出す
     *
     * @param requestUri ex:http://localhost:8080/index.html
     * @return UriPath ex:index.html
     */

    String parseFilePath(String requestUri) {
        int lastDotPosition = requestUri.lastIndexOf("/");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return UriPath;
    }

    /**
     * UriPathを取得する
     *
     * @return UriPath
     */

    public String getUriPath() {
        return this.UriPath;
    }



}



