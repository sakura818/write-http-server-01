package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/05/08.
 */
public class StatusCode {

    private int statusCode;
    private String status;
    private String reasonPhrase;



    public String statusCodeMap(int status) {
        final Map<Integer, String> STATUS_CODE = new HashMap<Integer, String>() {
            {
                put(200, "OK");
                put(400, "Bad Request");
                put(404, "Not Found");
            }
        };
        if (STATUS_CODE.containsKey(status)) {
            return STATUS_CODE + " " + STATUS_CODE.get(status);
        } else {
            return STATUS_CODE + "Unknown";
        }
    }

    /**
     * 現在設定されているステータスを取得します.
     *
     * @return ステータス
     */
    public String getStatus() {
        return this.status;
    }

}
