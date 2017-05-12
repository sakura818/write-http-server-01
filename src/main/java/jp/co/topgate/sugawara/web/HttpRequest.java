package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;


/**
 * HttpRequest class
 * HttpRequestを読み込む処理を行う
 * HttpRequest = Request-Line + MessageHeader + MessageBody
 * しかし、今回の課題では簡易的な機能しか提供しないためheaderやbodyは読み込んでいない
 * TODO:リクエストが表示されないエラーを解決する(while文のところ)　デバッグ
 *
 * @author sakura818
 */

public class HttpRequest {


    /**
     * HttpRequestをinputStreamから読み込む
     * BufferedReaderのreadLineメソッドを使用して行ごとに読み込んでいく
     *
     * @param inputStream
     * @return readRequestLine リクエストの1行目
     */

    public String readHttpRequest(InputStream inputStream) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String readRequestLine = bufferedReader.readLine();
            return readRequestLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

        spaceSeparateRequestLineArray = (readHttpRequest(inputStream).split(" ", 3));
        String requestUri = spaceSeparateRequestLineArray[1];

        String file = parseFile(requestUri);
        if (file.endsWith("/")) {
            file += "index.html";
        }
        return file;
    }

    /**
     * requestUriからファイル名を抜き出す
     *
     * @param requestUri 　ex:http://localhost:8080/index.html
     * @return file ex:index.html
     */

    public String parseFile(String requestUri) {
        int lastDotPosition = requestUri.lastIndexOf("/");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return null;
    }

    /**
     * HttpRequestに応じて適切なステータスコードを返す
     *
     * @param method ex:GET
     * @param file   ex:index.html
     * @return statusCode ex:200
     */

    public int selectStatusCode(String method, File file) {
        int statusCode;
        if (method == null) {
            statusCode = 400;
            return statusCode;
        }
        if (!file.exists()) {
            statusCode = 404;
            return statusCode;
        }
        statusCode = 200;
        return statusCode;
    }

}



