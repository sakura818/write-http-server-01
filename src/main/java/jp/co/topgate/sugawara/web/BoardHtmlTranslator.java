package jp.co.topgate.sugawara.web;


import java.io.IOException;
import java.util.List;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardHtmlTranslator {

    private List<OneMessage> list;
    StringBuffer stringBuffer = new StringBuffer();

    /**
     * コンストラクタ
     */

    public BoardHtmlTranslator(MessageList messageList) throws IOException {
        list = messageList.getList();
    }

    String firstHalfHtmlTenplate = ("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Board</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<h1>Board</h1>\n" +
            "<p></p>\n" +
            "\n" +
            "<section>\n" +
            "    <h2>新規投稿</h2>\n" +
            "    <form action=\"\" method=\"post\">\n" +
            "        Name: <br><input type=\"text\" name=\"name\" value=\"\"\"required><br>\n" +
            "        Text: <br><textarea name=\"text\" cols=\"30\" rows=\"3\" maxlength=\"80\" wrap=\"hard\"\n" +
            "                            placeholder=\"80字以内で入力してください \"required></textarea><br>\n" +
            "        Password: <br><input type=\"text\" name=\"password\" value=\"\"required><br>\n" +
            "        <input type=\"submit\" value=\"投稿する\" required >\n" +
            "    </form>\n" +
            "</section>\n" +
            "<section>\n" +
            "    <Hr>\n" +
            "    <h2>検索</h2>\n" +
            "    <p>検索した名前の人が行った書き込みを抽出して表示します</p>\n" +
            "    <form action=\"http://localhost:8080/program/board/search\" method=\"get\">\n" +
            "        Name: <br><input type=\"text\" name=\"name\" value=\"\" placeholder=\"4字以内で入力してください。\"><br>\n" +
            "        <input type=\"submit\" value=\"検索\" required>\n" +
            "    </form>\n" +
            "</section>\n" +
            "<section>\n");

    /**
     * 掲示板のトップページのHTMLをつくる
     *
     * @param messageList
     * @return HTMLをString型にしたもの
     */

    String topPageHtmlTranslator(MessageList messageList) throws IOException {
        stringBuffer.append(firstHalfHtmlTenplate);
        stringBuffer.append("<Hr>\n" + "<h2>投稿一覧</h2>\n" + "<Hr>\n");
        //TODO:ここ9行くらいの処理を他のメソッドでも似たように処理するが少しずつ違っているのでhtmlTenplateのようにまとめれずリファクタリングに困っている
        for (int i = 0; i < messageList.getList().size(); i++) {
            OneMessage oneMessage = messageList.getList().get(i);
            stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
            stringBuffer.append(oneMessage.getName() + " ");
            stringBuffer.append(oneMessage.getPostTime() + " ");
            stringBuffer.append(oneMessage.getText() + " ");
            stringBuffer.append("<form action=\"\" method=\"POST\">\n");
            stringBuffer.append("<input type=\"hidden\" name=\"_method\" value=\"DELETE\">");
            stringBuffer.append("<input type=\"hidden\" name=\"index\" value=\"").append(oneMessage.getIndex()).append("\">");
            stringBuffer.append("パスワード:<input type=\"password\" name=\"password\" required>");
            stringBuffer.append("<input type=\"submit\" value=\"この投稿を削除する\"></form>");
            stringBuffer.append("<Hr>\n");
        }
        stringBuffer.append("</section>\n" + "</body>\n" + "</html>");
        return stringBuffer.toString();
    }

    /**
     * 掲示板の削除のHTMLをつくる
     *
     * @param messageList
     * @return HTMLをString型にしたもの
     */

    String deleteHtmlTranslator(MessageList messageList) throws IOException {
        stringBuffer.append(firstHalfHtmlTenplate);
        stringBuffer.append("<Hr>\n" + "<h2>投稿一覧</h2>\n" + "<Hr>\n");
        if (messageList.isPasswordMatch() == true) {
            stringBuffer.append("投稿を削除しました" + "<Hr>\n");
        } else if (messageList.isPasswordMatch() == false) {
            stringBuffer.append("パスワードが間違っています" + "<Hr>\n");
        }
        for (int i = 0; i < messageList.getList().size(); i++) {
            OneMessage oneMessage = messageList.getList().get(i);
            stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
            stringBuffer.append(oneMessage.getName() + " ");
            stringBuffer.append(oneMessage.getPostTime() + " ");
            stringBuffer.append(oneMessage.getText() + " ");
            stringBuffer.append("<form action=\"\" method=\"POST\">\n");
            stringBuffer.append("<input type=\"hidden\" name=\"_method\" value=\"DELETE\">");
            stringBuffer.append("<input type=\"hidden\" name=\"index\" value=\"").append(oneMessage.getIndex()).append("\">");
            stringBuffer.append("パスワード:<input type=\"password\" name=\"password\" required>");
            stringBuffer.append("<input type=\"submit\" value=\"この投稿を削除する\"></form>");
            stringBuffer.append("<Hr>\n");
        }
        stringBuffer.append("</section>\n" + "</body>\n" + "</html>");
        return stringBuffer.toString();
    }

    /**
     * 名前で検索して合致した投稿結果を表示するHTMLをつくる
     *
     * @param messageList
     * @return HTMLをString型にしたもの
     */

    String searchNameHtmlTranslator(MessageList messageList, String queryNameParameter) throws IOException {
        stringBuffer.append(firstHalfHtmlTenplate);
        stringBuffer.append("<Hr>\n" + "<h2>検索結果</h2>\n" + "<Hr>\n");

        int searchResultNumber = 0;
        for (int i = 0; i < messageList.getList().size(); i++) {
            OneMessage oneMessage = null;
            if (queryNameParameter.equals(messageList.getList().get(i).getName())) {
                oneMessage = messageList.getList().get(i);
                stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
                stringBuffer.append(oneMessage.getName() + " ");
                stringBuffer.append(oneMessage.getPostTime() + " ");
                stringBuffer.append(oneMessage.getText() + " ");
                stringBuffer.append("<Hr>\n");
                searchResultNumber++;
            }
        }
        stringBuffer.append("検索結果");
        stringBuffer.append(searchResultNumber);
        stringBuffer.append("件" + "<Hr>\n");

        stringBuffer.append("<Hr>\n" + "<h2>投稿一覧</h2>\n" + "<Hr>\n");
        for (int i = 0; i < messageList.getList().size(); i++) {
            OneMessage oneMessage = messageList.getList().get(i);
            stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
            stringBuffer.append(oneMessage.getName() + " ");
            stringBuffer.append(oneMessage.getPostTime() + " ");
            stringBuffer.append(oneMessage.getText() + " ");
            stringBuffer.append("<form action=\"\" method=\"POST\">\n");
            stringBuffer.append("<input type=\"hidden\" name=\"_method\" value=\"DELETE\">");
            stringBuffer.append("<input type=\"hidden\" name=\"index\" value=\"").append(oneMessage.getIndex()).append("\">");
            stringBuffer.append("パスワード:<input type=\"password\" name=\"password\" required>");
            stringBuffer.append("<input type=\"submit\" value=\"この投稿を削除する\"></form>");
            stringBuffer.append("<Hr>\n");
        }
        stringBuffer.append("</section>\n" + "</body>\n" + "</html>");
        return stringBuffer.toString();
    }
}
