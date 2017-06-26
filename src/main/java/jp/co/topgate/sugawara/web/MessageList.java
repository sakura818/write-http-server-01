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
        // TODO:rename
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

    public List<OneMessage> appendMessage(String name, String text, String password) throws IOException {
        OneMessage oneMessage = list.get(list.size() - 1);
        int appendMessageIndex = oneMessage.getIndex() + 1;

        List<OneMessage> messageList = new ArrayList();
        for (int i = 0; i < this.list.size(); i++) {
            oneMessage = list.get(i);
            messageList.add(oneMessage);
        }
        postTime = measurePostTime();

        OneMessage appendOneMessage = new OneMessage(appendMessageIndex, name, postTime, text, password);
        messageList.add(appendOneMessage);
        this.list = messageList;
        return this.list;
    }

    /**
     * 投稿時間を測定する
     *
     * @return postTime
     */

    String measurePostTime() {
        ZonedDateTime postTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        this.postTime = postTime.toString();
        return this.postTime;
    }

    /**
     * 投稿1件削除を判断し、削除する場合はその投稿1件を削除した状態のリストを作成する
     *
     * @param index    リクエストのindex
     * @param password ブラウザで入力されたパスワード
     * @return 新しいリスト
     */

    public List<OneMessage> deleteMessageIfPasswordMatches(int index, String password) throws IOException {
        List<OneMessage> messageList = new ArrayList();
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
            messageList.add(oneMessage);
        }
        this.list = messageList;
        return list;
    }

    /**
     * 掲示板に1件投稿または1件削除して、その1件分新たに更新されたリストからCSVを新たに作成する
     */

    public void createUpdateCsvFromList() throws IOException {
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
     * SaveBoard.csvから投稿全件を読み込んで全件のリストを作成する
     *
     * @return list
     */

    public List<OneMessage> readSaveBoardCsv() throws IOException {
        List<OneMessage> messageList = new ArrayList<>();
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
                messageList.add(oneMessage);
            }
            bufferedReader.close();
        } catch (NumberFormatException e) {
            System.out.println("フォーマットエラーがありました");
        }
        this.list = messageList;
        return this.list;
    }
}


