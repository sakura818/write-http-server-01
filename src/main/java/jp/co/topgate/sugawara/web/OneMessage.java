package jp.co.topgate.sugawara.web;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by haruka.sugawara on 2017/06/15.
 */
public class OneMessage {

    int index;
    String name;
    String postTime;
    String text;
    String password;

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
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("./src/main/resources/", "SaveBoard.csv"), false));
        printWriter.write( this.index + "," + this.name + "," + this.postTime + "," + this.text + "," + this.password + "\n" );
        printWriter.close();
    }


    private int hoge;

    public

    /**
     * 投稿時間を測定する
     *
     * @return postTime
     */

    ZonedDateTime measureNewPostingTime() {
        ZonedDateTime postTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        return postTime;
    }

    /**
     * CSVファイルから投稿1件を削除する
     *
     * @param
     * @return
     */

    void deleteOneMessage() throws IOException{
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("./src/main/resources/", "SaveBoard.csv"),true));
        printWriter.write("hogehogehogehogehoge");
        printWriter.close();

    }

    /**
     * レス番号indexを取得する
     *
     * @return index
     */

    int getIndex() {
        return this.index;
    }

    /**
     * 名前nameを取得する
     *
     * @return name
     */

    String getName() {
        return this.name;
    }

    /**
     * 投稿時間posttimeを取得する
     *
     * @return postTime
     */

    String getPostTime() {
        return this.postTime;
    }

    /**
     * 本文textを取得する
     *
     * @return text
     */

    String getText() {
        return this.text;
    }

    /**
     * パスワードpasswordを取得する
     *
     * @return password
     */

    String getPassword() {
        return this.password;
    }

}
