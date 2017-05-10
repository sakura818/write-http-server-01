package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;


/**
 * HttpRequest class
 * リクエストを読み込んでmethodとfilepathを抜き出すのとステータスコードを決定する
 *
 * @author sakura818
 */
public class HttpRequest {


    /**
     * HttpRequestを行ごとに読み込む
     *
     * @param inputStream
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
     * TODO:リクエストUriをdecodeする
     * TODO:3つに分割できなかった場合の条件分岐 nullにする
     *
     * @param requestLine
     */

    public String spaceSeparateRequestLine(InputStream inputStream) {
        String[] spaceSeparateRequestLineArray = new String[2];

        spaceSeparateRequestLineArray =(readHttpRequest(inputStream).split(" ", 3));

        String filePath = parseFilePath(spaceSeparateRequestLineArray[1]);
        if (filePath.endsWith("/")) {
            filePath += "hello.html";
        }
        return filePath;
    }

    /**
     * requestUriからファイル名を抜き出す
     *
     * @param requestUri 　例えば
     * @return requestUriのパスを抜き取ったもの
     */

    public String parseFilePath(String requestUri) {
        try {
            URI requestUriPath = new URI(requestUri);
            return requestUriPath.getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}



