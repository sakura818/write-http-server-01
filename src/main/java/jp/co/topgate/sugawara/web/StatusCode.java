package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/05/08.
 */
public class StatusCode {

    private int statusCode;
    private String reasonPhrase;

    public String statusCodeMap(int statusCode) {
        final Map<Integer, String> requestLine_status = new HashMap<Integer, String>() {
            {
                put(200, "OK");
                put(400, "Bad Request");
                put(404, "Not Found");
            }
        };
        if (requestLine_status.containsKey(statusCode)) {
            reasonPhrase = requestLine_status.get(statusCode);
            return statusCode + " " + reasonPhrase;
        } else {
            return statusCode + "Unknown";
        }
    }

    /**
     * 現在設定されているステータスコードを取得します.
     *
     * @return ステータス
     */
    public int getStatusCode() {
        return this.statusCode;
    }

}
