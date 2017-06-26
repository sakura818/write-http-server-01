package jp.co.topgate.sugawara.web;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by haruka.sugawara on 2017/06/15.
 */
public class OneMessage {

    int index;
    String name;
    String postTime;
    String text;
    String password;

    /**
     * コンストラクタ
     */

    OneMessage(int index, String name, String postTime, String text, String password) {
        this.index = index;
        this.name = name;
        this.postTime = postTime;
        this.text = text;
        this.password = password;
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
