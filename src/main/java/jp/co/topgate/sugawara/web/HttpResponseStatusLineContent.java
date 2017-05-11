package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * HttpResponseStatusLineContent Class
 * HttpResponseのStatusLineを生成する
 * StatusLineとはHTTP-Version SP Status-Code SP Reason-Phrase CRLF
 * HttpResponseのContentを3つに分けたうちの1つである。
 *
 * @author sakura818
 */

public class HttpResponseStatusLineContent {

    private String responseStatusLineContent;

    public String getResponseStatusLineContent() {
        return this.responseStatusLineContent;
    }

    /**
     * ResponseStatusLineを生成する
     * Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
     *
     * @param statusCode ex:200
     * @return responseStatusLineContent ex:OK
     */

    public String createResponseStatusLine(int statusCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1").append(" ");
        stringBuilder.append(statusCode).append(" ");
        stringBuilder.append(getReasonPhrase());
        String responseStatusLineContent = new String(stringBuilder);
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

    private int statusCode;
    private String reasonPhrase;

    /**
     * テストのためにステータスコードを設定する
     *
     * @param i ステータスコード　ex:200
     */

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    /**
     * テストのために現在設定されているstatusCodeを取得する
     *
     * @return statusCode ex:200
     */

    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * テストのために現在設定されているreasonPhraseを取得する
     *
     * @return reasonPhrase ex:OK
     */

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }



}
