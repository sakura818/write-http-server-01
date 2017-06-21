package jp.co.topgate.sugawara.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseMessageBodyBuilder {
    private String html;
    private String query;

    /**
     * コンストラクタ
     *
     * @param
     * @return
     */

    public BoardDynamicHttpResponseMessageBodyBuilder(File file, String responseAssortFlag, HttpRequest httpRequest, InputStream inputStream, Map<String, String> requestBody) throws IOException {
        MessageList messageList = new MessageList();
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator(messageList);
        int index;
        String name;
        String text;
        String password;

        switch (responseAssortFlag) {
            // トップページ 投稿一覧を見ることが出来る
            case "topPage":
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            // 名前、本文、パスワードを入力して投稿を1件追加するとき
            case "postMessage":
                name = requestBody.get("name");
                text = requestBody.get("text");
                password = requestBody.get("password");
                int max = 0;
                List<OneMessage> readSaveBoardCsv = messageList.readSaveBoardCsv();
                for (int i = 0; i < readSaveBoardCsv.size(); i++) {
                    OneMessage oneMessage = readSaveBoardCsv.get(i);
                    System.out.println(oneMessage.getIndex());
                    int currentIndex = oneMessage.getIndex();
                    if (max >= currentIndex) {
                        max = max;
                    } else if (max < currentIndex) {
                        max = currentIndex;
                    }
                }
                System.out.println("max:" + max);
                index = max + 1;
                System.out.println("id:" + index);
                OneMessage appendOneMessage = new OneMessage(index, name, "", text, password);
                appendOneMessage.appendOneMessage();
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            // パスワードを入力して投稿1件を削除するとき
            case "deleteMessage":
                index = Integer.parseInt(requestBody.get("index"));
                password = requestBody.get("password");
                this.html = boardHtmlTranslator.boardDeleteHtml(messageList, index, password);
                break;
            // 入力した名前の人が投稿した投稿一覧を表示するとき
            case "searchName":
                String query = analyzeQueryString(httpRequest);
                this.html = boardHtmlTranslator.boardSearchNameHtml(messageList, query);
                break;
        }
    }

    /**
     * MessageBodyを生成する
     *
     * @return String型のHTMLをbyte[]型にしたもの
     */

    public byte[] build() {
        return getHtml().getBytes();
    }

    /**
     * HTMLを取得する
     *
     * @return html
     */

    String getHtml() {
        return this.html;
    }

    /**
     * クエリストリングを解析する
     * 今回は名前を検索する
     *
     * @param httpRequest
     * @return クエリ値
     */

    String analyzeQueryString(HttpRequest httpRequest) {
        String queryParams[] = httpRequest.getQueryString().split("=");
        System.out.println(httpRequest.getQueryString());
        System.out.println(queryParams[0]);
        System.out.println(queryParams[1]);
        query = queryParams[1];
        return query;
    }
}
