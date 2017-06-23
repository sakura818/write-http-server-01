package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public BoardDynamicHttpResponseMessageBodyBuilder(String responseAssortFlag, HttpRequest httpRequest, Map<String, String> requestBody) throws IOException {
        MessageList messageList = new MessageList();
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator(messageList);
        int index;
        String name;
        String text;
        String password;
        OneMessage oneMessage = null;

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
                messageList.appendMessage(name,text,password,oneMessage);
                messageList.newListToNewCsv();
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            // パスワードを入力して投稿1件を削除するとき
            case "deleteMessage":
                index = Integer.parseInt(requestBody.get("index"));
                password = requestBody.get("password");
                messageList.deleteMessage(index,password);
                messageList.newListToNewCsv();
                this.html = boardHtmlTranslator.boardDeleteHtml(messageList);
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
        query = queryParams[1];
        return query;
    }
}
