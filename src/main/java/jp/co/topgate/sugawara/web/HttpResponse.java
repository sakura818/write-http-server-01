package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpResponse {

    public static String httpResponseGenerate() {

        String httpResponseData = null;

        String statusLine = null;
        String responseHeader = null;
        String responseMessageBody = null;

        StringBuilder builder = new StringBuilder();
        //画像はbytestream

        builder.append(statusLine).append("\n");
        builder.append(responseHeader).append("\n");
        builder.append("\n");
        builder.append(responseMessageBody);

        System.out.println("response...");
        return builder.toString();

    }

    public boolean isFileExists(File file) {
        if (file.exists()) {
            //System.out.println("ファイルは存在しています");
            return true;
        } else {
            //System.out.println("ファイルは存在していません");
            return false;
        }
    }

    public String createStatusLine(boolean isFileExists) {

        String statusLine;

        if (isFileExists == true) {
            statusLine = "HTTP/1.1 200 OK";
        } else {
            statusLine = "HTTP/1.1 404 Not Found";
        }
        return statusLine;
    }

    public String distinguishContentType(String Extension) {

        String contentType = null;

        if ((Extension.equals("html")) || (Extension.equals("css")) || Extension.equals("js")) {
            contentType = "Content-Type: text/" + Extension;
        } else if ((Extension.equals("png")) || Extension.equals("jpeg") || Extension.equals("gif")) {
            contentType = "Content-Type: image/" + Extension;
        } else {
            contentType = "Content-Type: text/plain";
        }
        return contentType;
    }


    public String createEntityField(String contentType) {
        String entityField;
        entityField = contentType;
        return entityField;
    }


    public String createMessageBody(String messageBody) {

        return messageBody;

    }


    /* Java入門実践編　p254,p263のサンプルコードを参考に書いたコード
    * http://book.impress.co.jp/books/1114101003_4
    * テキストファイルを読み込んで表示するコード*/
    public static void textFileCat(String[] args) throws IOException {
        FileReader fr = null;//tryブロックの外でnullで初期化しないとfinallyブロックでcloseを呼べない
        System.out.println("テキストファイルのすべてのデータを一文字ずつ読んで表示します");
        try {
            String fileName = getRequestFile();
            fr = new FileReader(getRequestFileメソッドのfileName);//textfile読み込み
            int i = fr.read();
            {
                while (i != -1) {
                    char c = (char) i;
                    System.out.println(c);
                    i = fr.read();
                }
                System.out.println("ファイルの末尾に到達しました");
            }
        } catch (IOException e) {
            System.out.println("ファイルの書き込みに失敗しました");
        } finally {
            if (fr != null) {
                try {//closeがIOExceptionを発生させる可能性があるため再度try-catch文が必要。ただし失敗しても何もできないためcatchブロックは空。
                    fr.close();//必ずcloseされることが保証されなければならないためfinallyブロックに記述する
                } catch (IOException e2) {
                }

            }

        }
    }

    /* Java入門実践編　p257,p263を参考に書いたコード
    * バイナリファイルを読み込んで表示するコード*/
    public static void binaryFileCat(String[] args) throws IOException {
        FileInputStream fis = null;//tryブロックの外でnullで初期化しないとfinallyブロックでcloseを呼べない
        System.out.println("テキストファイルのすべてのデータを一文字ずつ読んで表示します");
        try {
            fis = new FileInputStream(getRequestFileメソッドのfileName);//textfile読み込み
            int i = fis.read();
            {
                while (i != -1) {
                    char c = (char) i;
                    System.out.println(c);
                    i = fis.read();
                }
                System.out.println("ファイルの末尾に到達しました");
            }
        } catch (IOException e) {
            System.out.println("ファイルの書き込みに失敗しました");
        } finally {
            if (fis != null) {
                try {//closeがIOExceptionを発生させる可能性があるため再度try-catch文が必要。ただし失敗しても何もできないためcatchブロックは空。
                    fis.close();//必ずcloseされることが保証されなければならないためfinallyブロックに記述する
                } catch (IOException e2) {
                }

            }

        }
    }

    /* Java入門実践編　p274のサンプルコード
    * バイナリファイルを読み込んで表示するコード
    STEP1:ファイル出力ストリームをfosを生成
    FIleInputStream fis = new FIleInputStream("data.txt");
    STEP2:このストリームを下流に持つ暗号化ストリームcosを接続　フィルタ1
    CipherInputStream cis = new CipherInputStream(fis,algo);//algoにも暗号化方式の指定が格納されているものとする
    STEP3:さらに文字バイトからバイトに変換するストリームoswを接続
    CipherStreamWriter osw = new InputStreamWriter(cis);
    STEP4:oswに文字を書き込めば、バイト変換＆暗号化されファイルに流されていく
    osw.write("AB");//これを実行すれば接続されているcosやfosのcloseも連鎖的に実行される
    */

    /*
    * ファイルを呼び出すときまたはリクエストを送信するとき、どちらの場合でバイト変換をしたほうがよいのか
    * すべてバイトに変換して対応する場合、プログラムは書きやすいが実際の処理の効率がすごく悪い
    * */





}

