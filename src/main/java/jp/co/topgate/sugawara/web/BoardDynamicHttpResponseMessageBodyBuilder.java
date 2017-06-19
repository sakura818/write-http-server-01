package jp.co.topgate.sugawara.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

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

    public BoardDynamicHttpResponseMessageBodyBuilder(File file, String assort, HttpRequest httpRequest, InputStream inputStream) throws IOException {
        if (assort.equals("topPage")) {
            System.out.println("ねこ");
        }
        analyzePostRequestBody(httpRequest, inputStream);
        analyzeQueryString(httpRequest);
        MessageList messageList = new MessageList();
        System.out.println(messageList.readSaveBoardCsv());
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator(messageList);
        this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
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

    String searchMessageByName() {
        return "";
    }

    /**
     * クエリストリングを解析する
     *
     * @param httpRequest
     * @return クエリ値
     */

    String analyzeQueryString(HttpRequest httpRequest) {
        httpRequest.getQueryString();
        System.out.println(httpRequest.getQueryString());
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
    Map<String, String> analyzePostRequestBody(HttpRequest httpRequest, InputStream inputStream) throws IOException {
        byte[] bodyInputStream = httpRequest.getMessageBody();
        System.out.println("hana");
        String messageBodyString = new String(bodyInputStream, "UTF-8");
        String[] hoge = messageBodyString.split("&");
        System.out.println(hoge.length);

        for (int count = 0; count <= hoge.length - 1; count++) {
            System.out.println(hoge[count]);
        }
        Map<String, String> messageBodyKey = new HashMap<String, String>();
        if(hoge.length != 1) {
            String[] line = hoge[0].split("=");
            messageBodyKey.put(line[0], line[1]);
            System.out.println(line[0]);
            System.out.println(line[1]);
            String[] line1 = hoge[1].split("=");
            messageBodyKey.put(line1[0], line1[1]);
            System.out.println(line1[0]);
            System.out.println(line1[1]);
            String[] line2 = hoge[2].split("=");
            messageBodyKey.put(line2[0], line2[1]);
            System.out.println(line2[0]);
            System.out.println(line2[1]);
        }
        this.messageBodykey = messageBodyKey;

        return messageBodyKey;

    }

    private Map<String,String> messageBodykey;
    Map<String,String> getMessageBodykey(){return this.messageBodykey;}

    String getHtml() {
        return this.html;
    }
}
