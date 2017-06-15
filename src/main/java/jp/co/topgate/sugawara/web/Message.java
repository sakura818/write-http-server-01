package jp.co.topgate.sugawara.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class Message {

    int index;
    String name;
    String postTime;
    String text;
    String password;
    private String fileName;
    private int num;

    /**
     * コンストラクタ
     *
     * @param
     * @return
     */

    Message() throws IOException {
        readSaveBoardCsv();
        this.fileName = fileName;
        this.num = num;

    }


    /**
     * csvファイルを読み込む
     *
     * @param
     * @return
     */


    public void readSaveBoardCsv() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("SaveBoard.csv"));
            String csvLine;

            while ((csvLine = br.readLine()) != null) {
                String saveBoardCsvArray[] = csvLine.split(",");
                int index = Integer.parseInt(saveBoardCsvArray[0]);
                String name = saveBoardCsvArray[1];
                String postTime = saveBoardCsvArray[2];
                String text = saveBoardCsvArray[3];
                String password = saveBoardCsvArray[4];
            }
            br.close();
        } catch (NumberFormatException e) {
            System.out.println("フォーマットエラーがありました");
        }
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


