package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * HttpResponseStatusLineContent Class
 * HttpResponseのStatusLineのContentを生成するクラス
 * StatusLine = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
 *
 * @author sakura818
 */

public class HttpResponseStatusLineContent {

    /**
     * ResponseStatusLineを生成する
     * Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
     *
     * @param statusCode ex:200
     * @return httpResponseStatusLineContent ex:OK
     */

    public String createHttpResponseStatusLine(int statusCode) {
        StringBuilder httpResponseStatusLine = new StringBuilder();
        httpResponseStatusLine.append("HTTP/1.1").append(" ");
        httpResponseStatusLine.append(statusCode).append(" ");
        httpResponseStatusLine.append(createReasonPhrase(statusCode));
        String httpResponseStatusLineContent = new String(httpResponseStatusLine);
        return httpResponseStatusLineContent;
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
}
