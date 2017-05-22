package jp.co.topgate.sugawara.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpResponseStatusLineBuilder Class
 * HttpResponseのStatusLineのContentを生成するクラス
 * StatusLine = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
 *
 * @author sakura818
 */

public class HttpResponseStatusLineBuilder {
    private int statusCode;

    public HttpResponseStatusLineBuilder(int statusCode){
        this.statusCode = statusCode;
    }

    /**
     * ResponseStatusLineを生成する
     * Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
     *
     * @param statusCode ex:200
     * @return httpResponseStatusLineContent ex:OK
     */

    public String build(int statusCode) {
        StringBuilder statusLine = new StringBuilder();
        statusLine.append("HTTP/1.1").append(" ");
        statusLine.append(statusCode).append(" ");
        statusLine.append(getReasonPhrase(statusCode));
        return statusLine.toString();
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

    public String getReasonPhrase(int statusCode) {
        if (statusCodeToReasonPhrase.containsKey(statusCode)) {
            return statusCodeToReasonPhrase.get(statusCode);
        }
        return null;
    }
}