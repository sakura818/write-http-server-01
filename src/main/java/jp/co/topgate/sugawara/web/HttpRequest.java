package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;


/**
 * HttpRequest class
 * リクエストを読み込んでfileを抜き出す
 *
 * @author sakura818
 */
public class HttpRequest {


    /**
     * HttpRequestを行ごとに読み込む
     *
     * @param inputStream
     * @return readLine リクエストの1行目
     */

    public String readHttpRequest(InputStream inputStream) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String readLine = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (!(readLine).equals(null)) {
                stringBuilder.append(readLine).append("\n");
            }
            System.out.println(stringBuilder);
            return readLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 空白文字を区切り文字としてrequestLineを3つに分割する
     * requestLine = method + requestUri(→filePath) + httpVersion
     * methodとhttpVersionは使用しないため配列の要素に追加しない
     *
     * @param inputStream
     * @return file 例えばindex.html
     */

    public String spaceSeparateRequestLine(InputStream inputStream) {
        String[] spaceSeparateRequestLineArray = new String[2];

        spaceSeparateRequestLineArray = (readHttpRequest(inputStream).split(" ", 3));
        String requestUri = spaceSeparateRequestLineArray[1];

        String file = parseFile(requestUri);
        if (file.endsWith("/")) {
            file += "hello.html";
        }
        return file;
    }

    /**
     * requestUriからファイル名を抜き出す
     *
     * @param requestUri 　例えばhttp://localhost:8080/index.html
     * @return file 例えばindex.html
     */

    public String parseFile(String requestUri) {
        int lastDotPosition = requestUri.lastIndexOf("/");
        if (lastDotPosition != -1) {
            return requestUri.substring(lastDotPosition + 1);
        }
        return null;
    }

}



