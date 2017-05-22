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
    private InputStream inputStream;
    private String method;
    private String filePath;

    public HttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        String requestLine = printHttpRequestLine();
        String[] requestLineArray = spaceSeparateRequestLine(requestLine);
        this.method = requestLineArray[0];
        String requestUri = requestLineArray[1];

        String filePath = parseFilePath(requestUri);
        if (filePath.endsWith("/")) {
            filePath += "index.html";
        }
        this.filePath = filePath;
    }

    /**
     * inputStreamからHttpRequestのRequestLineを読み込む
     * TODO:全ての行を表示したほうが良い　現状では1行目しか表示しない
     *
     * @return readRequestLine HttpRequestの1行目 ex:GET http://localhost:8080/index.html HTTP/1.1
     */

    public String printHttpRequestLine() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(this.inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String readHttpRequestLine = bufferedReader.readLine();
            System.out.println(readHttpRequestLine);
            return readHttpRequestLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * requestLineに対して空白文字をdelimiterとして3つに分割する
     * requestLine = method + requestUri + httpVersion
     * requestLine ex:GET http://localhost:8080/index.html HTTP/1.1
     * httpVersionは今回使用しない
     *
     * @param requestLine
     * @return filePath ex:index.html
     */

    public String[] spaceSeparateRequestLine(String requestLine) {
        return (requestLine.split(" ", 3));
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

    /**
     * filePathを取得する
     *
     * @return filepath
     */

    public String getFilePath() {
        return this.filePath;
    }


}



