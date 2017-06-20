package jp.co.topgate.sugawara.web;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/06/15.
 */
public class OneMessage {

    int index;
    String name;
    String postTime;
    String text;
    String password;
    private boolean isPasswordOfFormDataMatch;

    // 1件の投稿
    OneMessage(int index, String name, String postTime, String text, String password) {
        this.index = index;
        this.name = name;
        this.postTime = measureNewPostingTime().toString();
        this.text = text;
        this.password = password;
    }

    /**
     * 新規投稿1件をcsvに書き込む
     *
     * @param
     * @return
     */
    void appendOneMessage() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("./src/main/resources/", "SaveBoard.csv"), true));
        printWriter.write("\n" + this.index + "," + this.name + "," + this.postTime + "," + this.text + "," + this.password);
        printWriter.close();
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
     * CSVファイルから投稿1件を削除する
     *
     * @param
     * @return
     */

    String deleteOneMessage(String passwordOfFormData, boolean isPasswordOfFormDataMatch) {
        if (isPasswordOfFormDataMatch = true) {


        }
        return "";
    }

    /**
     * パスワードがあってるかどうか
     *
     * @param
     * @return
     */

    boolean isPasswordOfFormData(String passwordOfFormData, String csvPassword) {
        boolean isPasswordOfFormDataMatch = false;
        if (passwordOfFormData.equals(csvPassword)) {
            isPasswordOfFormDataMatch = true;
        }
        return isPasswordOfFormDataMatch;
    }


    public boolean isPasswordOfFormDataMatch() {
        return this.isPasswordOfFormDataMatch;
    }

    /**
     * レス番号indexを取得する
     *
     * @param
     * @return
     */

    int getIndex() {
        return this.index;
    }

    /**
     * 名前nameを取得する
     *
     * @param
     * @return
     */

    String getName() {
        return this.name;
    }

    /**
     * 投稿時間posttimeを取得する
     *
     * @param
     * @return
     */

    String getPostTime() {
        return this.postTime;
    }

    /**
     * 本文textを取得する
     *
     * @param
     * @return
     */

    String getText() {
        return this.text;
    }

    /**
     * パスワードpasswordを取得する
     *
     * @param
     * @return
     */

    String getPassword() {
        return this.password;
    }


}
