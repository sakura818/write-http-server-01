package jp.co.topgate.sugawara.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        this.fileName = fileName;
        this.num = num;

    }


    /**
     * csvファイルを読み込む
     *
     * @param
     * @return
     */


    public List<BoardData> readSaveBoardCsv() throws IOException {
        List<BoardData> list = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/resources/", "SaveBoard.csv")));
            String csvLine;
            System.out.println("csvファイル読み取ることができてる");

            while ((csvLine = br.readLine()) != null) {
                int i = 1;
                String saveBoardCsvArray[] = csvLine.split(",", 5);

                int index = Integer.parseInt(saveBoardCsvArray[0]);
                String name = saveBoardCsvArray[1];
                String postTime = saveBoardCsvArray[2];
                String text = saveBoardCsvArray[3];
                String password = saveBoardCsvArray[4];

                BoardData boardData = new BoardData(index, name, postTime, text, password);
                System.out.println(boardData);
                list = new ArrayList<BoardData>();// これは1件1件投稿の集合体
                list.add(boardData);// listに1つのBoardDataクラスのインスタンスを追加

            }

            br.close();
        } catch (NumberFormatException e) {
            System.out.println("フォーマットエラーがありました");
        }
        return list;
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


