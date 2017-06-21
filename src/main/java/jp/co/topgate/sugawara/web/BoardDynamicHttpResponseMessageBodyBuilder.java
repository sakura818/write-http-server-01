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

    public BoardDynamicHttpResponseMessageBodyBuilder(File file, String responseAssortFlag, HttpRequest httpRequest, InputStream inputStream, Map<String, String> requestBody) throws IOException {
        MessageList messageList = new MessageList();
        BoardHtmlTranslator boardHtmlTranslator = new BoardHtmlTranslator(messageList);

        switch (responseAssortFlag) {
            case "topPage":
                System.out.println("topPage");
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            case "postMessage":
                System.out.println("postMessage");
                String name = requestBody.get("name");
                String text = requestBody.get("text");
                String password = requestBody.get("password");
                int max = 0;
                for (int i = 0; i < messageList.readSaveBoardCsv().size(); i++) {
                    OneMessage oneMessage = messageList.readSaveBoardCsv().get(i);
                    System.out.println(oneMessage.getIndex());
                    if (max >= oneMessage.getIndex()) {
                        max = max;
                    } else if (max < oneMessage.getIndex()) {
                        max = oneMessage.getIndex();
                    }
                }
                System.out.println("max:" + max);
                int index = max + 1;
                System.out.println("id:" + index);
                OneMessage oneMessage = new OneMessage(index, name, "", text, password);
                oneMessage.appendOneMessage();
                this.html = boardHtmlTranslator.boardTopPageHtml(messageList);
                break;
            case "deleteMessage":
                System.out.println("deleteMessage");
                int resindex = Integer.parseInt(requestBody.get("index"));
                password = requestBody.get("password");

                this.html = boardHtmlTranslator.boardDeleteHtml(messageList, resindex, password);
                break;
            case "searchName":
                System.out.println("searchName");
                String queryNameParameter = analyzeQueryString(httpRequest);
                this.html = boardHtmlTranslator.boardSearchNameHtml(messageList, queryNameParameter);
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
        return queryNameParameter;
    }


    public String getQueryNameParameter() {
        return this.queryNameParameter;
    }


    String getHtml() {
        return this.html;
    }
}
