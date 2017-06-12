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
     *
     *
     * @param
     * @return
     */

    String convertCsvToMessage() {
        return "";
    }

    /**
     *
     *
     * @param
     * @return
     */

    int getIndex() {
        return 0;
    }

    /**
     *
     *
     * @param
     * @return
     */

    String getName() {
        return "";
    }

    /**
     *
     *
     * @param
     * @return
     */

    Date getPostTime() {
        return null;
    }

    /**
     *
     *
     * @param
     * @return
     */

    String getText() {
        return "";
    }

    /**
     *
     *
     * @param
     * @return
     */

    String getPassword() {
        return "";
    }

    /**
     *CSVファイルから投稿を削除する
     *
     * @param
     * @return
     */


    String deleteMessageFromSaveBoard() {
        return "";
    }

    /**
     *
     *
     * @param
     * @return
     */

    String appendMessageFromSaveBoard() {
        return "";
    }
}


