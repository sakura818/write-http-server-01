package jp.co.topgate.sugawara.web;


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
     * @param messageList
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
                "        Text: <br><textarea name=\"text\" cols=\"30\" rows=\"3\" maxlength=\"80\" wrap=\"hard\"\n" +
                "                            placeholder=\"80字以内で入力してください。\"></textarea><br>\n" +
                "        Password: <br><input type=\"text\" name=\"password\" value=\"\"><br>\n" +
                "        <input type=\"submit\" value=\"投稿する\">\n" +
                "    </form>\n" +
                "</section>\n" +
                "<section>\n" +
                "    <Hr>\n" +
                "    <h2>検索</h2>\n" +
                "    <p>検索した名前の人が行った書き込みを抽出して表示します</p>\n" +
                "    <form action=\"\" method=\"get\">\n" +
                "        Name: <br><input type=\"text\" name=\"name\" value=\"\" placeholder=\"4字以内で入力してください。\"><br>\n" +
                "        <input type=\"submit\" value=\"検索\">\n" +
                "    </form>\n" +
                "</section>\n" +
                "<section>\n");
        stringBuffer.append("<Hr>\n" + "<h2>投稿一覧</h2>\n" + "<Hr>\n");
        for (int i = 0; i < messageList.readSaveBoardCsv().size(); i++) {
            OneMessage oneMessage = messageList.readSaveBoardCsv().get(i);
            stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
            stringBuffer.append(oneMessage.getName() + " ");
            stringBuffer.append(oneMessage.getPostTime() + " ");
            stringBuffer.append(oneMessage.getText() + " ");
            stringBuffer.append(oneMessage.getPassword());
            stringBuffer.append("<form action=\"\" method=\"POST\">\n");
            stringBuffer.append("<input type=\"hidden\" name=\"_method\" value=\"DELETE\">");
            stringBuffer.append("<input type=\"hidden\" name=\"index\" value=\"").append(i + 1).append("\">");
            stringBuffer.append("パスワード:<input type=\"password\" name=\"password\">");
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
     * @return
     */

    String boardDeleteHtml(MessageList messageList, int resindex, String password) throws IOException {
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
                "        Text: <br><textarea name=\"text\" cols=\"30\" rows=\"3\" maxlength=\"80\" wrap=\"hard\"\n" +
                "                            placeholder=\"80字以内で入力してください。\"></textarea><br>\n" +
                "        Password: <br><input type=\"text\" name=\"password\" value=\"\"><br>\n" +
                "        <input type=\"submit\" value=\"投稿する\">\n" +
                "    </form>\n" +
                "</section>\n" +
                "<section>\n" +
                "    <Hr>\n" +
                "    <h2>検索</h2>\n" +
                "    <p>検索した名前の人が行った書き込みを抽出して表示します</p>\n" +
                "    <form action=\"\" method=\"get\">\n" +
                "        Name: <br><input type=\"text\" name=\"name\" value=\"\" placeholder=\"4字以内で入力してください。\"><br>\n" +
                "        <input type=\"submit\" value=\"検索\">\n" +
                "    </form>\n" +
                "</section>\n" +
                "<section>\n");
        stringBuffer.append("<Hr>\n" + "<h2>投稿一覧</h2>\n" + "<Hr>\n");

        // パスワードあってたら該当の投稿削除

        OneMessage deleteOneMessage = messageList.readSaveBoardCsv().get(resindex);
        if (deleteOneMessage.getPassword().equals(password)) {
            System.out.println("パスワードあっています");
        } else {
            System.out.println("パスワードが間違っています");
        }

        for (int i = 0; i < messageList.readSaveBoardCsv().size(); i++) {
            OneMessage oneMessage = messageList.readSaveBoardCsv().get(i);
            stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
            stringBuffer.append(oneMessage.getName() + " ");
            stringBuffer.append(oneMessage.getPostTime() + " ");
            stringBuffer.append(oneMessage.getText() + " ");
            stringBuffer.append(oneMessage.getPassword());
            stringBuffer.append("<form action=\"\" method=\"POST\">\n");
            stringBuffer.append("<input type=\"hidden\" name=\"_method\" value=\"DELETE\">");
            stringBuffer.append("<input type=\"hidden\" name=\"index\" value=\"").append(i + 1).append("\">");
            stringBuffer.append("パスワード:<input type=\"password\" name=\"password\">");
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
     * @return
     */

    String boardSearchNameHtml(MessageList messageList, String queryNameParameter) throws IOException {
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
                "    <form action=\"\" method=\"POST\">\n" +
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
                "    <form action=\"\" method=\"GET\">\n" +
                "        Name: <br><input type=\"text\" name=\"name\" value=\"\" placeholder=\"4字以内で入力してください。\"><br>\n" +
                "        <input type=\"submit\" value=\"検索\">\n" +
                "    </form>\n" +
                "</section>\n" +
                "<section>\n");
        stringBuffer.append("<Hr>\n" + "<h2>検索結果</h2>\n" + "<Hr>\n");


        for (int i = 0; i < messageList.readSaveBoardCsv().size(); i++) {
            OneMessage oneMessage = null;
            if (queryNameParameter.equals(messageList.readSaveBoardCsv().get(i).getName())) {
                oneMessage = messageList.readSaveBoardCsv().get(i);
                stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
                stringBuffer.append(oneMessage.getName() + " ");
                stringBuffer.append(oneMessage.getPostTime() + " ");
                stringBuffer.append(oneMessage.getText() + " ");
                stringBuffer.append(oneMessage.getPassword());
                stringBuffer.append("<Hr>\n");
            }
        }


        stringBuffer.append("<Hr>\n" + "<h2>投稿一覧</h2>\n" + "<Hr>\n");
        for (int i = 0; i < messageList.readSaveBoardCsv().size(); i++) {
            OneMessage oneMessage = messageList.readSaveBoardCsv().get(i);
            stringBuffer.append("[" + oneMessage.getIndex() + "]" + " ");
            stringBuffer.append(oneMessage.getName() + " ");
            stringBuffer.append(oneMessage.getPostTime() + " ");
            stringBuffer.append(oneMessage.getText() + " ");
            stringBuffer.append(oneMessage.getPassword());
            stringBuffer.append(" <form action=\"\" method=\"POST\">\n" +
                    "<input type=\"hidden\" name=\"_method\" value=\"DELETE\">" + //ここにもう1行追加
                    "パスワード:<input type=\"password\" name=\"password\">" +
                    "<input type=\"submit\" value=\"この投稿を削除する\"></form>");
            stringBuffer.append("<Hr>\n");
        }

        stringBuffer.append("</section>\n" + "</body>\n" + "</html>");

        return stringBuffer.toString();
    }

}
