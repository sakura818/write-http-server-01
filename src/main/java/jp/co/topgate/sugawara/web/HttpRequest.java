package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;


/**
 * HttpRequest class
 * HttpRequestを読み込む処理を行う
 * HttpRequest = Request-Line + MessageHeader + MessageBody
 * しかし、今回の課題では簡易的な機能しか提供しないためMessageHeaderとMessageBodyは読み込んでいない
 *
 * @author sakura818
 */

public class HttpRequest {

    /**
     * HttpRequestの全文をSystem.out.printlnで表示する
     * BufferedReaderのreadLineメソッドを使用して行ごとに読み込んでいく
     *
     * @param inputStream
     */

    public void sysoutHttpRequest(InputStream inputStream) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String sysoutHttpRequest = bufferedReader.readLine();
            while (sysoutHttpRequest != null && !sysoutHttpRequest.isEmpty()) { //TODO:
                System.out.println(sysoutHttpRequest);
            }
        } catch (IOException e) {
            System.out.println("HttpRequestの全文表示失敗");
            throw new RuntimeException(e);
        }
    }

    /**
     * RequestLineをinputStreamから読み込む
     *
     * @param inputStream
     * @return readRequestLine リクエストの1行目
     */

    public String readHttpRequestLine(InputStream inputStream) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String readHttpRequestLine = bufferedReader.readLine();
            return readHttpRequestLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String method;

    /**
     * requestLineに対して空白文字をdelimiterとして3つに分割する
     * requestLine = method + requestUri + httpVersion
     * ex: requestLine = GET http://localhost:8080/index.html HTTP/1.1
     * methodとhttpVersionは今回使用しない
     *
     * @param inputStream
     * @return filePath ex:index.html
     */

    public String spaceSeparateRequestLine(InputStream inputStream) {
        String[] spaceSeparateRequestLineArray;

        spaceSeparateRequestLineArray = (readHttpRequestLine(inputStream).split(" ", 3));
        this.method = spaceSeparateRequestLineArray[0];
        String requestUri = spaceSeparateRequestLineArray[1];

        String filePath = parseFilePath(requestUri);
        if (filePath.endsWith("/")) {
            filePath += "index.html";
        }
        return filePath;
    }

    /**
     * メソッドを取得する
     *
     * @return method
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * requestUriからfilePathを抜き出す
     *
     * @param requestUri 　ex:http://localhost:8080/index.html
     * @return filePath ex:index.html
     */

    public String parseFilePath(String requestUri) {
        if (requestUri == null) {
            return "";
        }
        int lastDotPosition = requestUri.lastIndexOf("/");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return filePath;
    }

    private String filePath;

    /**
     * filePathを取得する
     *
     * @return filepath
     */
    public String getFilePath() {
        return this.filePath;
    }


}



