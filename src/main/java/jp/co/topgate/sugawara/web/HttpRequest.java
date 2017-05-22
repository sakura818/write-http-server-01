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
    private InputStream inputStream;
    private String method;
    private String filePath;

    public HttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        String requestLine = readHttpRequestLine();
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
     * HttpRequestの全文をSystem.out.printlnで表示する
     * BufferedReaderのreadLineメソッドを使用して行ごとに読み込んでいく
     * TODO:
     */

    public void printHttpRequest() throws IOException {
        BufferedInputStream bufferedInputStream
                = new BufferedInputStream(this.inputStream);
        try {
            bufferedInputStream.read();
            System.out.println(bufferedInputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bufferedInputStream.close();
        }
    }

    /**
     * RequestLineをinputStreamから読み込む
     *
     * @return readRequestLine リクエストの1行目
     */

    public String readHttpRequestLine() {
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
     * ex: requestLine = GET http://localhost:8080/index.html HTTP/1.1
     * methodとhttpVersionは今回使用しない
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



