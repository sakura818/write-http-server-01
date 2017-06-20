package jp.co.topgate.sugawara.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class MessageList {

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

    MessageList() throws IOException {

    }


    /**
     * csvファイルを読み込む
     *
     * @param
     * @return
     */


    public List<OneMessage> readSaveBoardCsv() throws IOException {
        List<OneMessage> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/resources/", "SaveBoard.csv")));
            String csvLine;

            while ((csvLine = br.readLine()) != null) {
                String saveBoardCsvArray[] = csvLine.split(",", 5);

                int index = Integer.parseInt(saveBoardCsvArray[0]);
                String name = saveBoardCsvArray[1];
                String postTime = saveBoardCsvArray[2];
                String text = saveBoardCsvArray[3];
                String password = saveBoardCsvArray[4];

                OneMessage oneMessage = new OneMessage(index, name, postTime, text, password);
                list.add(oneMessage);

            }

            br.close();
        } catch (NumberFormatException e) {
            System.out.println("フォーマットエラーがありました");
        }
        return list;
    }

}


