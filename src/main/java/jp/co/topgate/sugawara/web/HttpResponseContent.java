package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.*;

/**
 * レスポンスのコンテンツを生成するクラス
 *
 * @author sakura818
 */

public class HttpResponseContent {
    private String httpResponseContent;

    public String getHttpResponseContent() {
        return this.httpResponseContent;
    }

    /**
     * レスポンスのコンテンツを生成
     * createHttpResponseContent = createResponseStatusLine + createResponseMessageHeader + createResponseMessageBody
     *
     * @param statusCode
     * @return httpResponseContent
     */
    public String createHttpResponseContent(int statusCode, String file) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HttpResponseStatusLineContent.getResponseStatusLineContent()).append("\n");
        stringBuilder.append(HttpResponseMessageHeaderContent.getResponseMessageHeaderContent()).append("\n");
        stringBuilder.append(createResponseMessageBody()).append("\n");
        String httpResponseContent = new String(stringBuilder);
        return httpResponseContent;
    }

}
