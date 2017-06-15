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


}
