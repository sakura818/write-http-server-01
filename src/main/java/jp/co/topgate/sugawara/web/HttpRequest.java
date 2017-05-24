package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.util.regex.Pattern;


/**
 * HttpRequest class
 * inputStreamからHttpRequestを読み込む処理を行う
 * HttpRequest = Request-Line + MessageHeader + MessageBody
 * 今回の課題では簡易的な機能しか提供しないためMessageHeaderとMessageBodyは読み込んでいない
 *
 * @author sakura818
 */

public class HttpRequest {
    private String file;
    private boolean isCorrectRequestLine;
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
        printRequestLine(requestLine);
        String requestUri = splitRequestLine(requestLine);
        //this.isCorrectRequestLine = isCorrectRequestLine;

        String file = parseFilePath(requestUri);
        if (file.endsWith("/")) {
            file += "index.html";
        }
        this.file = file;
    }


    /**
     * HttpRequestのRequestLineを表示する
     *
     * @param requestLine
     */
    void printRequestLine(String requestLine) {
        System.out.println(requestLine);
    }

    /**
     * requestLineに対して空白文字をdelimiterとしてmethod,requestUri,httpVersionの3つに分割する
     * requestLine = method + requestUri + httpVersion
     * requestLine ex:GET http://localhost:8080/index.html HTTP/1.1
     * httpVersionは今回使用しない
     * TODO:requestLineが適切な形かチェックしてOKだったらboolean isCorrectRequestLineをtrueに、OKじゃない場合はboolean isCorrectRequestLineをfalseにしようと考えていた。
     * TODO:↑falseのときはstatusCodeを400にしようとしていたが失敗。400のときにrequestUriをどう設定していいのかも把握すべき。
     *
     * @param requestLine
     * @return file ex:index.html
     */

    String splitRequestLine(String requestLine) {
        String[] requestLineArray = requestLine.split(" ", 3);
        /*
        if (requestLineArray.length == 3 && requestLineArray[0] == "GET" && requestLineArray[2] == "HTTP/1.1") {
             isCorrectRequestLine　== true;
             requestUri = requestLineArray[1];
        }
        */
        requestUri = requestLineArray[1];
        return requestUri;
    }

    /**
     * requestUriからfileを抜き出す
     *
     * @param requestUri ex:http://localhost:8080/index.html
     * @return file ex:index.html
     */

    String parseFilePath(String requestUri) {
        int lastDotPosition = requestUri.lastIndexOf("/");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return file;
    }

    /**
     * fileを取得する
     *
     * @return file
     */

    public String getFile() {
        return this.file;
    }


    /**
     * isCorrectRequestLineを取得する
     *
     * @return isCorrectRequestLine
     */

    public boolean getIsCorrectRequestLine() {
        return this.isCorrectRequestLine;
    }

}



