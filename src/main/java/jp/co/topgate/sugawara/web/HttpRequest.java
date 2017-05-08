package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;


/**
 * HttpRequest class
 * リクエストを分解する
 *
 * @author sakura818
 */
public class HttpRequest {

    private String requestLine;
    private String method;
    private String requestUri;
    private String httpVersion;
    private String filePath;

    /*
    methodの処理 getter
     */

    public String getMethod() {
        return this.method;
    }

    /*
    requestUriの処理 getter
     */

    public String getFilePath() {
        return this.filePath;
    }


    /**
     * HttpRequestを行ごとに分割し読み込む
     *
     * @param inputStream
     */

    public void readRequest(InputStream inputStream) {
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            String requestLine = br.readLine();

            this.requestLineDivide(requestLine);
            StringBuilder sb = new StringBuilder();

            while (!(requestLine).equals("")) {
                sb.append(requestLine).append("¥n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 空白文字を区切り文字としてrequestLineを3つに分割する
     * requestLine = method + requestUri + httpVersion
     *
     * @param requestLine
     */
    public void requestLineDivide(String requestLine) {
        String requestUriDelimiterDivide[] = (requestLine.split(" "));

        this.method = requestUriDelimiterDivide[0];
        this.filePath = requestUriPath(requestUriDelimiterDivide[1]);
        if (this.filePath.endsWith("/")) {
            this.filePath += "hello.html";
        }
        this.httpVersion = requestUriDelimiterDivide[2];
    }


    /**
     * requestUriからパス名を抜き出す
     */
    public String requestUriPath(String requestUri) {
        try {
            URI requestUriPath = new URI(requestUri);
            return requestUriPath.getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);

        }
    }


}
