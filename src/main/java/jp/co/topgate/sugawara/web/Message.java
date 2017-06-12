package jp.co.topgate.sugawara.web;

import java.util.Date;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class Message {

    int index;
    String name;
    Date postTime;
    String text;
    String password;

    /**
     *コンストラクタ
     *
     * @param
     * @return
     */

    Message() {
    }

    /**
     *csvファイルから投稿の1件として取得する
     *
     * @param
     * @return
     */

    String convertCsvToMessage() {
        return "";
    }

    /**
     * レス番号indexを取得する
     *
     * @param
     * @return
     */

    int getIndex() {
        return 0;
    }

    /**
     * 名前nameを取得する
     *
     * @param
     * @return
     */

    String getName() {
        return "";
    }

    /**
     * 投稿時間posttimeを取得する
     *
     * @param
     * @return
     */

    Date getPostTime() {
        return null;
    }

    /**
     * 本文textを取得する
     *
     * @param
     * @return
     */

    String getText() {
        return "";
    }

    /**
     * パスワードpasswordを取得する
     *
     * @param
     * @return
     */

    String getPassword() {
        return "";
    }

    /**
     * CSVファイルから投稿を削除する
     *
     * @param
     * @return
     */


    String deleteMessageFromSaveBoard() {
        return "";
    }

    /**
     * CSVファイルに新規投稿を追加する
     *
     * @param
     * @return
     */

    String appendMessageFromSaveBoard() {
        return "";
    }
}


