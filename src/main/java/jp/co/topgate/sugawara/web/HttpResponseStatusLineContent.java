package jp.co.topgate.sugawara.web;

import java.io.File;

/**
 * Created by haruka.sugawara on 2017/05/11.
 */
public class HttpResponseStatusLineContent {

    /**
     * ResponseStatusLineを生成する
     * Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
     *
     * @param statusCode
     * @return responseStatusLineContent
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
     * テストのためにステータスコードを設定する
     *
     * @param i ステータスコード　例えば200
     */

    public void setStatusCode(int i) {
        this.statusCode = i;
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
     * テストのために現在設定されているreasonPhraseを取得する
     *
     * @return reasonPhrase
     */

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }


    /**
     * リクエストに応じて適切なステータスコードを返す
     */

    public int selectStatusCode(String method, File file) {
        int statusCode;
        if (method == null) {
            statusCode = 400;
            return statusCode;
        }
        if (!file.exists()) {
            statusCode = 404;
            return statusCode;
        }
        statusCode = 200;
        return statusCode;
    }
}
