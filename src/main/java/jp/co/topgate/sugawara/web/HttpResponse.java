package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpResponse {

    private static final String FILE_DIR = "src/main/java/resources/";
    private String filepath;
    private String statusLine;
    private String extension;


    public String getFilepath() {
        return this.filepath;
    }

    public String getStatusLine() {
        return this.statusLine;
    }


    public String httpResponseGenerate() {

        String httpResponseData;

        String responseHeader = "";
        String responseMessageBody = "";

        StringBuilder builder = new StringBuilder();

        builder.append(statusLine).append("\n");
        builder.append(responseHeader).append("\n");
        builder.append("\n");
        builder.append(responseMessageBody);

        System.out.println("response...");
        return builder.toString();

    }

    public String fileExistsStatusLine() throws IOException, URISyntaxException {
        HttpRequest requestPath = new HttpRequest();
        String filepath = FILE_DIR + requestPath.requestUriDecodeAndPath();
        File file = new File(filepath);
        if (file.exists() == true) {
            String statusLine = "HTTP/1.1 200 OK";
            return statusLine;
        } else {
            String statusLine = "HTTP/1.1 404 Not Found";
            return statusLine;
        }
    }


    public String createMessageBody(String messageBody) {

        return messageBody;

    }



    /* Java入門実践編　p257,p263を参考に書いたコード
    * バイナリファイルを読み込んで表示するコード*/

    public void binaryFileCat(String[] args) throws IOException {
        FileInputStream fis = null;//tryブロックの外でnullで初期化しないとfinallyブロックでcloseを呼べない
        System.out.println("バイナリファイルのすべてのデータを一文字ずつ読んで表示します");
        try {
            fis = new FileInputStream(this.filepath);//binaryfile読み込み
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
    content-typeの一覧
     */

    public final Map<String, String> CONTENT_TYPE = new HashMap<String, String>() {
        {
            // specifications
            put("html", "text/html");
            put("htm", "text/html");
            put("css", "text/css");
            put("js", "application/javascript");
            put("jpg", "image/jpeg");
            put("jpeg", "image/jpeg");
            put("png", "image/png");
            put("gif", "image/gif");

            // option
            put("txt", "text/plain");
            put("pdf", "application/pdf");
            put("mp4", "video/mp4");

            //default
            put("octet-stream", "application/octet-stream");
        }
    };


    /*
    ファイルの拡張子を取得する　もしかしたらファイルから拡張子を推測するほうを使ったほうがいいかもしれない
     */
    public String findExtension() {
        int lastDotPosition = this.filepath.lastIndexOf(".");
        if (lastDotPosition != -1) {
            extension = this.filepath.substring(lastDotPosition + 1);
            return extension;
        } else {
            return "neko";
        }
    }

    /*
    ContentTypeを生成する
     */

    public String extensionToContentType() {

        if (CONTENT_TYPE.containsKey(extension)) {
            System.out.print("extensionのContent-Typeは");
            System.out.println(CONTENT_TYPE.get(extension));
            return "ContentType: " + CONTENT_TYPE.get(extension);
        } else {
            return "指定したCONTENT_TYPEのキーは存在しません";
        }

    }

    /*
    HTTP-dateを生成する
     */
    public String getHttpDateTime() {
        SimpleDateFormat rfc1123DateFormat =
                new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz", java.util.Locale.US);
        rfc1123DateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date httpDateTime = Calendar.getInstance().getTime();
        return rfc1123DateFormat.format(httpDateTime);
    }


    /*
    generalHeaderを生成する
     */

    public String generateGeneralHeader() {

        StringBuilder sb = new StringBuilder();
        //sb.append("Cache-Control: " + "¥n");// cacheに関することなので今回は使用しない
        //sb.append("Connection: " + "¥n");// 永続的接続Keep-AliveがHTTP/1.1ではデフォルトである。これを無効にしたいときにConnection: closeとする　今回は使用しない
        sb.append("Date: " + getHttpDateTime() + "¥n");// メッセージが生成された日付・時刻を表す。ィールド値は、section 3.3.1 にて示される HTTP-date であり、RFC 1123 [8] の時刻フォーマット
        //sb.append("Pragma: " + "¥n");// cacheに関することなので今回は使用しない
        //sb.append("Trailer: " + "¥n");// チャンク形式で今回は送らないので無視する
        //sb.append("Transfer-Encoding: " + "¥n");// 転送エンコーディングchunkなどを今回は使用しないので無視する
        //sb.append("Upgrade: " + "¥n");// Upgrade 一般ヘッダは、クライアントが他にどんな通信プロトコルをサポートするかを表し、サーバがプロトコルを切り換えた方がいいと判断した場合に使わせたいものを指定させる。ex:Upgrade: HTTP/2.0, SHTTP/1.3 今回はHTTP/1.1しか想定していないため無視する
        //sb.append("Via: " + "¥n");// proxyに関することなので今回は無視する
        //sb.append("Warning: " + "¥n");// キャッシュの処理やメッセージのエンティティボディに適用される変化から意味的透過性が欠けている可能性がある事を警告する。今回は無視する

        String generalHeader = new String(sb);
        return generalHeader;
    }


    /*
    responseHeaderを生成する
     */

    public String generateResponseHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Accept-Ranges:" + "none" + "¥n");// リソースへのいかなる範囲リクエストも受け入れない
        //sb.append("Age: " + "age-value");// Age レスポンスヘッダは、オリジンサーバがレスポンスを生成した (あるいは再検証した) 時点からの送信者の推定経過時間を示す。キャッシュエントリが新鮮かどうかを知るために、キャッシュは経過時間が その有効期間を超えているかどうかを知る必要がある。
        sb.append("hoge" + "¥n");
        sb.append("Server: " + "sakura818uuu" + "¥n");// Serverレスポンスヘッダフィールドは、リクエストを処理するオリジンサーバが使っているソフトウェアについての情報を含んでいる。ex:Server: Apache[Ver]
        sb.append("hoge" + "¥n");
        sb.append("hoge" + "¥n");

        String responseHeader = new String(sb);
        return responseHeader;
    }

    /*
    entityHeaderを生成する
     */

    public String generateEntityHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append(extensionToContentType() + "¥n");
        sb.append("def" + "¥n");
        sb.append("ghi" + "¥n");

        String entityHeader = new String(sb);
        return entityHeader;
    }


    /*
    ResponseMessageHeaderを生成する
    ResponseMessageHeader = generalHeader + responseHeader + entityHeader
     */

    public String generateResponseMessageHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append(generateGeneralHeader() + "¥n");
        sb.append(generateResponseHeader() + "¥n");
        sb.append(generateEntityHeader() + "¥n");

        String str = new String(sb);
        return str;
    }


}

