package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseMessageBodyBuilder {
    private String html;

    /**
     * コンストラクタ
     *
     * @param
     * @return
     */

    public BoardDynamicHttpResponseMessageBodyBuilder(File file, String assort, HttpRequest httpRequest) throws IOException {
        if (assort.equals("topPage")) {
            System.out.println("ねこ");
        }
        MessageList messageList = new MessageList();
        System.out.println(messageList.readSaveBoardCsv());
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator(messageList);
        this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
        /*
        for (int i = 0; i < message.readSaveBoardCsv().size(); i++) {
            System.out.println(message.readSaveBoardCsv().get(i));
        }
        */


    }

    /**
     * @param
     * @return
     */

    public byte[] build() {
        return getHtml().getBytes();
    }

    /**
     * @param
     * @return
     */
/*
    public byte[] boardShow() {
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator();
        return boardHtmlTranslator.boardTopPageHtml().getBytes();
    }
    */


    /**
     * @param
     * @return
     */


    String searchMessageByName() {
        return "";
    }

    /**
     * クエリストリングを解析する
     *
     * @param queryString クエリストリング
     * @return クエリ値
     */

    String analyzeQueryString(String queryString) {
        return "";
    }

    /**
     * パスワードで投稿を削除する
     *
     * @param
     * @return
     */

    String deleteMessageByPassword() {
        return "";
    }


    /**
     * リクエストのインスタンスからパスワードを解析する
     *
     * @param
     * @return
     */
    String analyzePasswordRequestBody() {
        return "";
    }


    /**
     * 名前、本文、パスワードを入力したものを1件の投稿として掲示板に新規投稿する
     *
     * @param
     * @return
     */
    String newPosting() {
        return "";
    }

    /**
     * リクエストのインスタンスのから名前、本文、パスワードを解析する
     *
     * @param
     * @return
     */
    String analyzePostRequestBody() {
        return "";
    }


    /**
     * 投稿時間を測定する
     *
     * @param
     * @return
     */
    ZonedDateTime measureNewPostingTime() {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTimeNow);
        return zonedDateTimeNow;
    }


    /**
     * @param
     * @return
     */
    String createMessage() {
        return "";
    }

    String getHtml() {
        return this.html;
    }
}
