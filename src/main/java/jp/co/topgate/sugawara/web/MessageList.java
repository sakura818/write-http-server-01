package jp.co.topgate.sugawara.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */

public class MessageList {
    private List<OneMessage> list;
    String postTime;

    public boolean isPasswordMatch() {
        return isPasswordMatch;
    }

    private boolean isPasswordMatch;


    /**
     * コンストラクタ
     */

    MessageList() throws IOException {
        readSaveBoardCsv();
        this.list = list;
    }

    public List<OneMessage> getList() {
        return this.list;
    }

    /**
     * 投稿用に新しいリストを作成する
     *
     * @param name     ブラウザで入力された名前
     * @param text     ブラウザで入力された本文
     * @param password ブラウザで入力されたパスワード
     * @return 新しいリスト
     */

    public List<OneMessage> appendMessage(String name, String text, String password, OneMessage oneMessage) throws IOException {
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            oneMessage = list.get(i);
            int currentIndex = oneMessage.getIndex();
            if (max >= currentIndex) {
                max = max;
            } else if (max < currentIndex) {
                max = currentIndex;
            }
        }
        int index = max + 1;

        List<OneMessage> oneMessageAppendList = new ArrayList();
        for (int i = 0; i < this.list.size(); i++) {
            oneMessage = list.get(i);
            oneMessageAppendList.add(oneMessage);
        }
        postTime = measureNewPostingTime();

        OneMessage appendOneMessage = new OneMessage(index, name, postTime, text, password);
        oneMessageAppendList.add(appendOneMessage);
        this.list = oneMessageAppendList;
        return this.list;
    }

    /**
     * 投稿時間を測定する
     *
     * @return postTime
     */

    String measureNewPostingTime() {
        ZonedDateTime postTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        this.postTime = postTime.toString();
        return this.postTime;
    }

    /**
     * 削除用に新しいリストを作成する
     *
     * @param index    リクエストのindex
     * @param password ブラウザで入力されたパスワード
     * @return 新しいリスト
     */

    public List<OneMessage> deleteMessage(int index, String password) throws IOException {
        List<OneMessage> oneMessageDeleteList = new ArrayList();
        for (int i = 0; i < this.list.size(); i++) {
            OneMessage oneMessage = list.get(i);
            if (oneMessage.getIndex() == index) {
                BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
                if (bCrypt.matches(password, oneMessage.getPassword())) {
                    isPasswordMatch = true;
                    continue;
                } else {
                    isPasswordMatch = false;
                }
            }
            oneMessageDeleteList.add(oneMessage);
        }
        this.list = oneMessageDeleteList;
        return list;
    }

    /**
     * 新しいリストの内容をCSVファイルに新しく書き直す
     */

    public void newListToNewCsv() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("./src/main/resources/", "SaveBoard.csv"), false));
        List<OneMessage> updateList = getList();
        for (int i = 0; i < updateList.size(); i++) {
            OneMessage oneMessage = updateList.get(i);
            printWriter.write(String.valueOf(oneMessage.getIndex()));
            String comma = ",";
            printWriter.write(comma);
            printWriter.write(oneMessage.getName());
            printWriter.write(comma);
            printWriter.write(oneMessage.getPostTime());
            printWriter.write(comma);
            printWriter.write(oneMessage.getText());
            printWriter.write(comma);
            printWriter.write(oneMessage.getPassword());
            printWriter.write("\n");
        }
        printWriter.close();
    }

    /**
     * csvから投稿全件を読み込んでリストにする
     *
     * @return list
     */

    public List<OneMessage> readSaveBoardCsv() throws IOException {
        List<OneMessage> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./src/main/resources/", "SaveBoard.csv")));
            String csvLine;
            while ((csvLine = bufferedReader.readLine()) != null) {
                String saveBoardCsvArray[] = csvLine.split(",", 5);

                int index = Integer.parseInt(saveBoardCsvArray[0]);
                String name = saveBoardCsvArray[1];
                String postTime = saveBoardCsvArray[2];
                String text = saveBoardCsvArray[3];
                String password = saveBoardCsvArray[4];

                OneMessage oneMessage = new OneMessage(index, name, postTime, text, password);
                list.add(oneMessage);
            }
            bufferedReader.close();
        } catch (NumberFormatException e) {
            System.out.println("フォーマットエラーがありました");
        }
        this.list = list;
        return this.list;
    }
}


