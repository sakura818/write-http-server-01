package jp.co.topgate.sugawara.web;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardHtmlTranslator {

    /**
     * コンストラクタ
     *
     * @param
     * @return
     */

    public BoardHtmlTranslator(MessageList messageList) throws IOException {
        boardTopPageHtml(messageList);
    }


    /**
     * 掲示板のトップページのHTMLをつくる
     *
     * @param
     * @return
     */

    String boardTopPageHtml(MessageList messageList) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<!DOCTYPE html>\n" +
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
                "        Name: <br><input type=\"text\" name=\"name\" value=\"\" placeholder=\"4字以内で入力してください。\"><br>\n" +
                "        Text: <br><textarea name=\"comment\" cols=\"30\" rows=\"3\" maxlength=\"80\" wrap=\"hard\"\n" +
                "                            placeholder=\"80字以内で入力してください。\"></textarea><br>\n" +
                "        Password: <br><input type=\"text\" name=\"text\" value=\"\"><br>\n" +
                "        <input type=\"submit\" value=\"投稿する\">\n" +
                "    </form>\n" +
                "</section>\n" +
                "<section>\n" +
                "    <Hr>\n" +
                "    <h2>検索</h2>\n" +
                "    <p>検索した名前の人だけが行った書き込みだけが抽出されて表示されます</p>\n" +
                "    <form action=\"\" method=\"post\">\n" +
                "        Name: <br><input type=\"text\" name=\"name\" value=\"\" placeholder=\"4字以内で入力してください。\"><br>\n" +
                "        <input type=\"submit\" value=\"検索\">\n" +
                "    </form>\n" +
                "</section>\n" +
                "<section>\n");
        stringBuffer.append("    <Hr>\n"+"    <h2>投稿一覧</h2>\n" +
                "    <Hr>\n");
        for (int i = 0; i < messageList.readSaveBoardCsv().size(); i++) {
            OneMessage oneMessage = messageList.readSaveBoardCsv().get(i);
            stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
            stringBuffer.append(oneMessage.getName() + " ");
            stringBuffer.append(oneMessage.getPostTime() + " ");
            stringBuffer.append(oneMessage.getText() + " ");
            stringBuffer.append(oneMessage.getPassword());
            stringBuffer.append("<Hr>\n");
        }
        stringBuffer.append("</section>\n" +
                "</body>\n" +
                "</html>");

        return stringBuffer.toString();
    }

    /**
     * 名前で検索して合致した投稿結果を表示するHTMLをつくる
     *
     * @param
     * @return
     */

    String createSearchMessageByName() {
        return "";
    }

    /**
     * パスワードで削除された結果を表示するHTMLをつくる
     *
     * @param
     * @return
     */
    String createDeleteMessageByPassword() {
        return "";
    }

    /**
     * 新たに新規投稿検索した結果を表示するHTMLをつくる
     *
     * @param
     * @return
     */
    String createNewPostingHtml() {
        return "";
    }


}
