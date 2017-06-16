package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseStatusLineBuilder {
    private int statusCode;

    /**
     * コンストラクタ
     *
     * @param statusCode
     */

    public BoardDynamicHttpResponseStatusLineBuilder(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * StatusLineを生成する
     *
     * @return httpResponseStatusLineContent ex:HTTP/1.1 200 OK
     */

    public byte[] build() {
        StringBuilder statusLine = new StringBuilder();
        statusLine.append("HTTP/1.1").append(" ");
        statusLine.append(this.statusCode).append(" ");
        statusLine.append(catchReasonPhrase(this.statusCode)).append("\n");
        return (statusLine.toString()).getBytes();
    }

    /**
     * statusCodeとreasonPhraseのMap
     */

    final Map<Integer, String> statusCodeToReasonPhrase = new HashMap<Integer, String>() {
        {
            put(200, "OK");
            put(400, "Bad Request");
            put(404, "Not Found");
            put(501, "Not Implemented");
            put(505, "Http Version Not Supported");
        }
    };

    /**
     * reasonPhraseを生成する
     * reasonPhraseとはstatusCodeを説明するメッセージ
     *
     * @param statusCode ex:200
     * @return reasonPhrase ex:OK
     */

    String catchReasonPhrase(int statusCode) {
        if (statusCodeToReasonPhrase.containsKey(statusCode)) {
            return statusCodeToReasonPhrase.get(statusCode);
        }
        return "Unknown Status Code";
    }
}
