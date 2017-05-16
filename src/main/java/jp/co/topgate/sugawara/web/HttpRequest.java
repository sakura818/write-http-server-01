package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;


/**
 * HttpRequest class
 * HttpRequestを読み込む処理を行う
 * HttpRequest = Request-Line + MessageHeader + MessageBody
 * しかし、今回の課題では簡易的な機能しか提供しないためheaderやbodyは読み込んでいない
 *
 * @author sakura818
 */

public class HttpRequest {


    /**
     * HttpRequestの全文を表示する
     *
     * @param inputStream
     */

    public void showHttpRequest(InputStream inputStream) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String readHttpRequest = bufferedReader.readLine();
            while (readHttpRequest != null && !readHttpRequest.isEmpty()) {
                System.out.println(readHttpRequest);
            }
        } catch (IOException e) {
            System.out.println("HttpRequestの全文表示失敗");
            throw new RuntimeException(e);
        }
    }

    /**
     * HttpRequestをinputStreamから読み込む
     * BufferedReaderのreadLineメソッドを使用して行ごとに読み込んでいく
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

    /**
     * requestLineを空白文字をdelimiterとして3つに分割し、requestUriを編集してfileにする
     * requestLine = method + requestUri + httpVersion
     * ex: requestLine = GET http://localhost:8080/index.html HTTP/1.1
     * methodとhttpVersionは今回使用しない
     *
     * @param inputStream
     * @return file ex:index.html
     */

    public String spaceSeparateRequestLine(InputStream inputStream) {
        String[] spaceSeparateRequestLineArray;

        spaceSeparateRequestLineArray = (readHttpRequestLine(inputStream).split(" ", 3));
        this.method = spaceSeparateRequestLineArray[0];
        String requestUri = spaceSeparateRequestLineArray[1];

        String file = parseFile(requestUri);
        if (file.endsWith("/")) {
            file += "index.html";
        }
        return file;
    }

    private String method;

    /**
     * メソッドを取得する
     *
     * @return method
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * requestUriからファイル名を抜き出す
     *
     * @param requestUri 　ex:http://localhost:8080/index.html
     * @return file ex:index.html
     */

    public String parseFile(String requestUri) {
        if (requestUri == null) {
            return "";
        }
        int lastDotPosition = requestUri.lastIndexOf("/");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return file;
    }

    private String file;

    /**
     * 要求するリソースのローカルパスを取得します.
     *
     * @return file
     */
    public String getFile() {
        return this.file;
    }


}



