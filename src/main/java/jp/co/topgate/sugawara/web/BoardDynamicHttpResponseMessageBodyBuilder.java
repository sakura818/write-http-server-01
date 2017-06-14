package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseMessageBodyBuilder {

    /**
     * コンストラクタ
     *
     * @param
     * @return
     */

    public BoardDynamicHttpResponseMessageBodyBuilder(File file, String assort, HttpRequest httpRequest) {
        if(assort=="searchName") {System.out.println("ねこ");}
    }

    /**
     * @param
     * @return
     */

    public byte[] build() {
        return "".getBytes();
    }

    /**
     * @param
     * @return
     */


    String searchMessageByName() {
        return "";
    }

    /**
     * クエリストリングを解析する
     *
     * @param queryString クエリストリング
     * @return クエリ値
     */

    String analyzeQueryString(String queryString) {
        return "";
    }

    /**
     * パスワードで投稿を削除する
     *
     * @param
     * @return
     */

    String deleteMessageByPassword() {
        return "";
    }


    /**
     * リクエストのインスタンスからパスワードを解析する
     *
     * @param
     * @return
     */
    String analyzePasswordRequestBody() {
        return "";
    }


    /**
     * 名前、本文、パスワードを入力したものを1件の投稿として掲示板に新規投稿する
     *
     * @param
     * @return
     */
    String newPosting() {
        return "";
    }

    /**
     * リクエストのインスタンスのから名前、本文、パスワードを解析する
     *
     * @param
     * @return
     */
    String analyzePostRequestBody() {
        return "";
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
     * @param
     * @return
     */
    String createMessage() {
        return "";
    }
}
