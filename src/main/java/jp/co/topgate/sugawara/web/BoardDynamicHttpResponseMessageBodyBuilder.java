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
    private String nameOfFormData;
    private String textOfFromData;
    private String passwordOfFormData;
    private String queryNameParameter;
    private Map<String, String> messageBodykey;

    /**
     * コンストラクタ
     *
     * @param
     * @return
     */

    public BoardDynamicHttpResponseMessageBodyBuilder(File file, String responseAssortFlag, HttpRequest httpRequest, InputStream inputStream) throws IOException {
        MessageList messageList = new MessageList();
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator(messageList);

        switch (responseAssortFlag) {
            case "topPage":
                System.out.println("topPage");
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            case "postMessage":
                System.out.println("postMessage");
                analyzePostRequestBody(httpRequest);
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            case "deleteMessage":
                System.out.println("deleteMessage");
                analyzePostRequestBody(httpRequest);
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            case "searchName":
                System.out.println("searchName");
                analyzeQueryString(httpRequest);
                this.html = boardHtmlTranslator.boardSearchNameHtml(messageList);
                break;
        }

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
        String query[] = httpRequest.getQueryString().split("=");
        System.out.println(httpRequest.getQueryString());
        System.out.println(query[0]);
        System.out.println(query[1]);
        queryNameParameter = query[1];
        return query[0];
    }


    public String getQueryNameParameter() {
        return this.queryNameParameter;
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
        List<OneMessage> list = new ArrayList<>();
        if (getMessageBodykey().containsKey("name")) {
            System.out.println(getMessageBodykey().get("name"));
        } else {
            System.out.println("指定したキーは存在しません");
        }
        if (getMessageBodykey().containsKey("comment")) {
            System.out.println(getMessageBodykey().get("comment"));
        } else {
            System.out.println("指定したキーは存在しません");
        }
        if (getMessageBodykey().containsKey("text")) {
            System.out.println(getMessageBodykey().get("text"));
        } else {
            System.out.println("指定したキーは存在しません");
        }

        return null;
    }


    String getNameOfFormData() {
        return this.nameOfFormData;
    }

    String getTextOfFromData() {
        return this.textOfFromData;
    }

    String getPasswordOfFormData() {
        return this.passwordOfFormData;
    }

    /**
     * リクエストのインスタンスのから名前、本文、パスワードを解析する
     *
     * @param
     * @return
     */
    Map<String, String> analyzePostRequestBody(HttpRequest httpRequest) throws IOException {
        byte[] bodyInputStream = httpRequest.getMessageBody();
        System.out.println("hana");
        String messageBodyString = new String(bodyInputStream, "UTF-8");
        String[] hoge = messageBodyString.split("&");
        System.out.println(hoge.length);

        for (int count = 0; count <= hoge.length - 1; count++) {
            System.out.println(hoge[count]);
        }
        Map<String, String> messageBodyKey = new HashMap<String, String>();
        if (hoge.length != 1) {
            String[] line = hoge[0].split("=");
            messageBodyKey.put(line[0], line[1]);
            System.out.println(line[0]);
            System.out.println(line[1]);
            this.nameOfFormData = line[1];
            String[] line1 = hoge[1].split("=");
            messageBodyKey.put(line1[0], line1[1]);
            System.out.println(line1[0]);
            System.out.println(line1[1]);
            this.textOfFromData = line1[1];
            String[] line2 = hoge[2].split("=");
            messageBodyKey.put(line2[0], line2[1]);
            System.out.println(line2[0]);
            System.out.println(line2[1]);
            this.passwordOfFormData = line2[1];

            for (int i = 0; i <= 1; i++) {
                String[] neko = hoge[i].split("=");
                messageBodyKey.put(line[0], line[1]);

            }
        }
        this.messageBodykey = messageBodyKey;

        return messageBodyKey;

    }


    Map<String, String> getMessageBodykey() {
        return this.messageBodykey;
    }

    String getHtml() {
        return this.html;
    }
}
