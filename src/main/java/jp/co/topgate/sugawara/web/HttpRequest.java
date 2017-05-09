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
 * TODO:あとの課題のためにheaderとかmessagebody読み込むのも必要かもしれない
 * TODO：テスト作っててホスト必要かもしれないと思った
 *
 * @author sakura818
 */
public class HttpRequest {

    private String method;
    private String filePath;

    /**
     * methodをかえすgetter
     *
     * @return method
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * filePathをかえすgetter
     *
     * @return filePath
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
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String requestLine = bufferedReader.readLine();

            this.spaceSeparateRequestLine(requestLine);
            StringBuilder stringBuilder = new StringBuilder();

            while (!(requestLine).equals("")) {
                stringBuilder.append(requestLine).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 空白文字を区切り文字としてrequestLineを3つに分割する
     * requestLine = method + requestUri(→filePath) + httpVersion
     * TODO:リクエストUriをdecodeする
     * TODO:3つに分割できなかった場合の条件分岐 nullにする
     *
     * @param requestLine
     */

    public void spaceSeparateRequestLine(String requestLine) {
        String spaceSeparateRequestLineArray[] = (requestLine.split(" "));

        this.method = spaceSeparateRequestLineArray[0];
        this.filePath = partRequestUriPath(spaceSeparateRequestLineArray[1]);
        if (this.filePath.endsWith("/")) {
            this.filePath += "hello.html";
        }
        String httpVersion = spaceSeparateRequestLineArray[2];
    }

    /**
     * requestUriからパス名を抜き出す
     *
     * @param requestUri
     * @return requestUriのパスだけを抜き取ったもの
     */

    public String partRequestUriPath(String requestUri) {
        try {
            URI requestUriPath = new URI(requestUri);
            return requestUriPath.getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}



