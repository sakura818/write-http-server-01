package jp.co.topgate.sugawara.web;

import java.io.IOException;
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

    public BoardDynamicHttpResponseMessageBodyBuilder(String responseAssort, String queryString, Map<String, String> requestBody, String rawPassword,HttpRequest httpRequest) throws IOException {// TODO:引数
        MessageList messageList = new MessageList();
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator(messageList);
        int index;
        String name;
        String text;
        String encodePassword;

        switch (responseAssort) {
            // トップページ 投稿一覧を見ることが出来る
            case "topPage":
                this.html = boardHtmlTranslator.topPageHtmlTranslator(messageList);
                break;
            // 名前、本文、パスワードを入力して投稿を1件追加するとき
            case "postMessage":
                name = requestBody.get("name");
                text = requestBody.get("text");
                encodePassword = requestBody.get("password");
                messageList.appendMessage(name, text, encodePassword);
                messageList.createUpdateCsvFromList();
                this.html = boardHtmlTranslator.topPageHtmlTranslator(messageList);
                break;
            // パスワードを入力して投稿1件を削除するとき
            case "deleteMessage":
                index = Integer.parseInt(requestBody.get("index"));
                messageList.deleteMessageIfPasswordMatches(index, rawPassword);
                messageList.createUpdateCsvFromList();
                this.html = boardHtmlTranslator.deleteHtmlTranslator(messageList);
                break;
            // 入力した名前の人が投稿した投稿一覧を表示するとき
            case "searchName":
                String query = analyzeQueryString(queryString);
                this.html = boardHtmlTranslator.searchNameHtmlTranslator(messageList, query);
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

    String analyzeQueryString(String queryString) {// TODO:analyze抽象的すぎるかもしれない、加えてこのメソッドの内容だと複数クエリは対処できない（今回は名前１人の検索だから成り立ってる）、analyzeQuerStringってよりはqueryを返すものだから命名
        String queryParams[] = queryString.split("=");
        query = queryParams[1];
        return query;
    }
}
