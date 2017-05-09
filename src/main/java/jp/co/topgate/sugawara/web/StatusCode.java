package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * StatusCode Class
 * リクエストに応じて適切なステータスコードを判断するクラス
 * @author sakura818
 */
public class StatusCode {

    private int statusCode;
    private String reasonPhrase;
    private String statusCodeAndReasonPhrase;

    /**
     * ステータスコードを引数にstatuscode+空白+reasonPhraseを戻り値とする
     *
     * @param statusCode 例えば200
     */

    public String mappingStatusCode(int statusCode) {
        final Map<Integer, String> mapStatusCode = new HashMap<Integer, String>() {
            {
                put(200, "OK");
                put(400, "Bad Request");
                put(404, "Not Found");
            }
        };
        if (mapStatusCode.containsKey(statusCode)) {
            reasonPhrase = mapStatusCode.get(statusCode);
            return this.statusCode + " " + reasonPhrase;
        } else {
            return this.statusCode + "Unknown";
        }
    }

    /**
     * テストのためにステータスコードを設定する
     *
     * @param i ステータスコード　例えば200
     */
    public void setStatusCode(int i) {
        this.statusCode = i;
        this.statusCodeAndReasonPhrase = mappingStatusCode(i);
    }

    /**
     * テストのために現在設定されているstatusCodeを取得する
     *
     * @return statusCode
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * テストのために現在設定されているreasonPhraseを取得する。
     *
     * @return reasonPhrase
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    /**
     * テストのために現在設定されているstatusCodeとreasonPhraseを取得する
     *
     * @return statusCode and reasonPhrase
     */
    public String getStatusCodeAndReasonPhrase() {
        return this.statusCodeAndReasonPhrase;
    }

}
