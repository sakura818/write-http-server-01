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

    //1件の投稿
    OneMessage(int index, String name, String postTime, String text, String password) {
        this.index = index;
        this.name = name;
        this.postTime = postTime;
        this.text = text;
        this.password = password;
    }


    /**
     * 解析したデータから新規投稿1件を生成する
     *
     * @param
     * @return
     */

    void createOneMessage(String nameOfFormData, String textOfFormData, String passwordOfFormData) {
        this.index = index;
        this.name = nameOfFormData;
        this.postTime = measureNewPostingTime().toString();
        this.text = textOfFormData;
        this.password = passwordOfFormData;
    }

    /**
     * 新規投稿1件をcsvに書き込む
     *
     * @param
     * @return
     */
    void appendOneMessage() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("./src/main/resources/", "SaveBoard.csv")));
        bufferedWriter.write("createOneMessage");
        bufferedWriter.close();
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
