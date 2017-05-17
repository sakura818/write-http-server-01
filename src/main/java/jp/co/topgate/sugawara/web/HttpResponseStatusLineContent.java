package jp.co.topgate.sugawara.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpResponseStatusLineContent Class
 * HttpResponseのStatusLineのContentを生成するクラス
 * StatusLineとはHTTP-Version SP Status-Code SP Reason-Phrase CRLF
 * HttpResponseのContentを3つに分けたうちの1つである。
 *
 * @author sakura818
 */

public class HttpResponseStatusLineContent {

    private int statusCode;
    private String reasonPhrase;

    public HttpResponseStatusLineContent(int statusCode) {
    }


    /**
     * ResponseStatusLineを生成する
     * Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
     *
     * @param statusCode ex:200
     * @return responseStatusLineContent ex:OK
     */

    public String createResponseStatusLine(int statusCode) {
        StringBuilder responseStatusLine = new StringBuilder();
        responseStatusLine.append("HTTP/1.1").append(" ");
        responseStatusLine.append(statusCode).append(" ");
        responseStatusLine.append(createReasonPhrase(statusCode));
        String responseStatusLineContent = new String(responseStatusLine);
        return responseStatusLineContent;
    }

    /**
     * statusCodeとreasonPhraseのMap
     */

    private static final Map<Integer, String> statusCodeToReasonPhrase = new HashMap<Integer, String>() {
        {
            put(200, "OK");
            put(400, "Bad Request");
            put(404, "Not Found");
        }
    };

    /**
     * reasonPhraseを生成する
     * reasonPhraseとはstatusCodeを説明するメッセージ
     *
     * @param statusCode ex:200
     * @return reasonPhrase ex:OK
     */

    public String createReasonPhrase(int statusCode) {
        if (statusCodeToReasonPhrase.containsKey(statusCode)) {
            return statusCodeToReasonPhrase.get(statusCode);
        }
        return null;
    }


    /**
     * テストのためにステータスコードを設定する
     *
     * @param i ステータスコード　ex:200
     */

    public void setStatusCode(int i) {
        this.statusCode = i;
    }



}
