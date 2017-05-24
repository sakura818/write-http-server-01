package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.util.Arrays;

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
        String requestUri = getRequestUri(requestLine);

        String UriPath = parseUriPath(requestUri);
        if (UriPath.endsWith("/")) {
            UriPath += "index.html";
        }
        this.uriPath = UriPath;
    }


    /**
     * requestLineからrequestUriをgetする
     * requestLine = method + requestUri + httpVersion
     * requestLine ex:GET http://localhost:8080/index.html HTTP/1.1
     * //TODO:if文に条件が複数あると実行されない
     *
     * @param requestLine
     * @return UriPath ex:index.html
     */

    String getRequestUri(String requestLine) {
        String[] requestLineArray = requestLine.split(" ", 3);
        System.out.println("OK1");
        System.out.println(Arrays.toString(requestLineArray));
        System.out.println(requestLineArray[0]);
        System.out.println(requestLineArray[1]);
        System.out.println(requestLineArray[2]);

        if ((requestLineArray.length == 3) && ("GET".equals(requestLineArray[0])) && ("HTTP/1.1".equals(requestLineArray[2]))) {
            System.out.println("OK2");
            requestUri = requestLineArray[1];
            System.out.println("OK3");
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

    String parseUriPath(String requestUri) {
        int lastDotPosition = requestUri.lastIndexOf("/");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return uriPath;
    }

    /**
     * UriPathを取得する
     *
     * @return UriPath
     */

    public String getUriPath() {
        return this.uriPath;
    }


}



