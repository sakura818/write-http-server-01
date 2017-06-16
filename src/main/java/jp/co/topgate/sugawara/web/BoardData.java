package jp.co.topgate.sugawara.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by haruka.sugawara on 2017/06/15.
 */
public class BoardData {

    int index;
    String name;
    String postTime;
    String text;
    String password;

    //1件の投稿
    BoardData(int index, String name, String postTime, String text, String password) {
        this.index = index;
        this.name = name;
        this.postTime = postTime;
        this.text = text;
        this.password = password;
        System.out.println(index);
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
