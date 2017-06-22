package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class MessageList {

    /**
     * コンストラクタ
     *
     * @param
     * @return
     */

    private List<OneMessage> list;

    MessageList() throws IOException {
        readSaveBoardCsv();
        this.list = list;
    }

    public List<OneMessage> getList() {
        return this.list;
    }


    // 投稿1件追加する
    public List<OneMessage> appendMessage(String name, String text, String password, OneMessage oneMessage) throws IOException {
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            oneMessage = list.get(i);
            System.out.println(oneMessage.getIndex());
            int currentIndex = oneMessage.getIndex();
            if (max >= currentIndex) {
                max = max;
            } else if (max < currentIndex) {
                max = currentIndex;
            }
        }
        int index = max + 1;
        OneMessage appendOneMessage = new OneMessage(index, name, "", text, password);
        appendOneMessage.appendOneMessage();
        List<OneMessage> oneMessageAppendList = new ArrayList();
        oneMessageAppendList = readSaveBoardCsv();

        this.list = oneMessageAppendList;
        return this.list;

    }

    // 投稿1件を削除する
    // 新しいリストを作成
    public List<OneMessage> deleteMessage(int index, String password, OneMessage oneMessage) throws IOException{
        List<OneMessage> oneMessageDeleteList = new ArrayList();
        for (int i = 0; i < this.list.size(); i++) {
            oneMessage = list.get(i);
            if (oneMessage.getIndex() == index) {
                if (oneMessage.getPassword().equals(password)) {
                    System.out.println("password correct");
                    oneMessage.deleteOneMessage();
                    continue;
                }else{
                    System.out.println("password incorrect");
                }
            }
            oneMessageDeleteList.add(oneMessage);
        }
        this.list = oneMessageDeleteList;
        return list;
    }

    // 新しいリストの内容をCSVに上書きして新しいCSVをつくる
    public void newListToNewCsv(List<OneMessage> oneMessageDeleteList) throws IOException{
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("./src/main/resources/", "SaveBoard.csv"),false));
        for(int i = 0 ; i< oneMessageDeleteList.size(); i++) {
            OneMessage oneMessage = oneMessageDeleteList.get(i);
            printWriter.write(oneMessage.getIndex());
            printWriter.write(",");
            printWriter.write(oneMessage.getName());
            printWriter.write(",");
            printWriter.write(oneMessage.getPostTime());
            printWriter.write(",");
            printWriter.write(oneMessage.getText());
            printWriter.write(",");
            printWriter.write(oneMessage.getPassword());
            printWriter.write("\n");
        }
        printWriter.close();
    }

    //　新しいCSVから新しいリストを作成する
    public List<OneMessage> newCSVToNewList() throws  IOException{
        List<OneMessage> oneMessageDeleteList = new ArrayList<>();
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
       return this.list = oneMessageDeleteList;
    }




    /**
     * 投稿全件を読み込む
     *
     * @param
     * @return
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


