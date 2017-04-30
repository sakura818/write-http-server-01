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

        StringBuilder builder = new StringBuilder();

        builder.append(statusLine).append("\n");
        builder.append(generateResponseMessageHeader()).append("\n");
        builder.append("\n");
        builder.append(generateResponseMessageBody());

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



    /* Java入門実践編　p257,p263を参考に書いたコード
    * バイナリファイルを読み込んで表示するコード*/

    public void binaryFileShow(String[] args) throws IOException {
        FileInputStream fis = null;//tryブロックの外でnullで初期化しないとfinallyブロックでcloseを呼べない
        System.out.println("バイナリファイルのすべてのデータを一文字ずつ読んで表示します" + "¥n");
        try {
            fis = new FileInputStream(this.filepath);//binaryfile読み込み
            int i = fis.read();
            {
                while (i != -1) {
                    char c = (char) i;
                    System.out.println(c);
                    i = fis.read();
                }
                System.out.println("ファイルの末尾に到達しました" + "¥n");
            }
        } catch (IOException e) {
            System.out.println("ファイルの書き込みに失敗しました" + "¥n");
        } finally {
            if (fis != null) {
                try {//closeがIOExceptionを発生させる可能性があるため再度try-catch文が必要。ただし失敗しても何もできないためcatchブロックは空。
                    fis.close();//必ずcloseされることが保証されなければならないためfinallyブロックに記述する
                } catch (IOException e2) {
                }

            }

        }
    }

     /*
    content-typeの一覧
     */

    public final Map<String, String> CONTENT_TYPE = new HashMap<String, String>() {
        {
            // specifications
            put("html", "text/html; charset=utf-8");
            put("htm", "text/html; charset=utf-8");
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
    ファイルの拡張子を取得する　もしかしたらファイルのデータの中身から拡張子を推測するほうを使ったほうがよいかも
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
    public String generateHttpDateTime() {
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
        //sb.append("Cache-Control: " + "¥n");
        //sb.append("Connection: " + "¥n");
        sb.append("Date: " + generateHttpDateTime() + "¥n");// メッセージが生成された日付・時刻を表す。RFC 1123 の時刻フォーマット
        //sb.append("Pragma: " + "¥n");
        //sb.append("Trailer: " + "¥n");
        //sb.append("Transfer-Encoding: " + "¥n");
        //sb.append("Upgrade: " + "¥n");
        //sb.append("Via: " + "¥n");
        //sb.append("Warning: " + "¥n");

        String generalHeader = new String(sb);
        return generalHeader;
    }


    /*
    responseHeaderを生成する
     */

    public String generateResponseHeader() {

        StringBuilder sb = new StringBuilder();
        //sb.append("Accept-Ranges:" + "none" + "¥n");
        //sb.append("Age: " + "¥n");
        //sb.append("ETag: " + "¥n");
        //sb.append("Location: " + "¥n");
        //sb.append("Proxy-Authenticate: " + "¥n");
        //sb.append("Retry-After:" + "¥n");// 503(Service Unavailable) レスポンスと共に使われる。
        sb.append("Server: " + "sakura818uuu" + "¥n");// リクエストを処理するオリジンサーバが使っているソフトウェアについての情報を含んでいる。ex:Server: Apache[Ver]
        //sb.append("Vary: " + "¥n");
        //sb.append("WWW-Authenticate: " + "¥n");


        String responseHeader = new String(sb);
        return responseHeader;
    }

    /*
    entityHeaderを生成する
     */

    public String generateEntityHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Allow: " + "GET, HEAD" + "¥n");// Request-URI によって識別されるリソースがサポートするメソッドの一覧
        //sb.append("Content-Encoding: " + "¥n");
        sb.append("Content-Language: " + "ja, en" + "¥n");
        sb.append("Content-Length: " + "3495" + "¥n");// entity body size
        //sb.append("Content-Location:" + "¥n");
        //sb.append("Content-MD5: " + "¥n");
        //sb.append("Content-Range: " + "¥n");
        sb.append("Content-Type: " + extensionToContentType() + "¥n");
        //sb.append("Expires: " + "¥n");
        //sb.append("Last-Modified: " + "¥n");

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

        String responseMessageHeader = new String(sb);
        return responseMessageHeader;
    }



    /*
    ResponseMessageBodyを生成する
     */

    public String generateResponseMessageBody() {

        return "messageBody";

    }


}

