package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HttpResponse class
 * レスポンスを生成する
 *
 * @author sakura818
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

    /**
     * レスポンスの部品を集めて組み立て生成
     */

    public void generateHttpResponse() throws IOException {

        String httpResponseData;
        HttpRequest httpRequest = new HttpRequest();

        StringBuilder sb = new StringBuilder();

        sb.append(fileExistsStatusLine()).append("\n");
        sb.append(generateResponseMessageHeader()).append("\n");
        if(httpRequest.getMethod() == "GET"){
        sb.append(generateResponseMessageBody());
        }

        System.out.println("response...");
        System.out.println(sb.toString());

    }

    /**
     * ファイルの存在有無を確認し、ステータスコードを返す。
     */

    public String fileExistsStatusLine()  { // rename
        HttpRequest requestPath = new HttpRequest();
        try {
            String filepath = FILE_DIR + requestPath.requestUriDecodeAndPath();
            File file = new File(filepath);
            if (file.exists()) {
                String statusLine = "HTTP/1.1 200 OK";
                return statusLine;
            } else {
                String statusLine = "HTTP/1.1 404 Not Found";
                return statusLine;
            }
        } catch (IOException e) {
            //throw new IOException();
            String statusLine = "HTTP/1.1 500 Internal Server Error";
            return statusLine;
        } catch (URISyntaxException e) {
            //throw new URISyntaxException();
            String statusLine = "HTTP/1.1 400 Bad Request";
            return statusLine;
        }
    }




    /* Java入門実践編　p257,p263を参考に書いたコード
    * バイナリファイルを読み込んで表示するコード*/

    public void binaryFileShow(String[] args) throws IOException {
        FileInputStream fis = null;// tryブロックの外でnullで初期化しないとfinallyブロックでcloseを呼べない
        System.out.println("バイナリファイルのすべてのデータを一文字ずつ読んで表示します" + "¥n");
        try {
            fis = new FileInputStream(this.filepath);// binaryfile読み込み
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
                try {// closeがIOExceptionを発生させる可能性があるため再度try-catch文が必要。ただし失敗しても何もできないためcatchブロックは空。
                    fis.close();// 必ずcloseされることが保証されなければならないためfinallyブロックに記述する
                } catch (IOException e2) {
                }

            }

        }
    }

    /**
     * content-typeの一覧のmap
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


    /**
     * ファイルの拡張子を取得する
     */

    public String determineFileExtension() {
        int lastDotPosition = this.filepath.lastIndexOf(".");
        if (lastDotPosition != -1) {
            extension = this.filepath.substring(lastDotPosition + 1);
            return extension;
        } else {
            return "neko";
        }
    }

    /**
     * entityheaderに使われるcontent-type行を生成
     */
    public String extensionToContentType() {

        if (CONTENT_TYPE.containsKey(extension)) {
            return "ContentType: " + CONTENT_TYPE.get(extension);
        } else {
            return "指定したCONTENT_TYPEのキーは存在しません";
        }

    }

    /**
     * generalheaderに使われるメッセージが生成された日付・時刻を表すDate行を生成
     * RFC 1123 の時刻フォーマット
     */
    public String generateHttpDateTime() {
        SimpleDateFormat HttpDateFormat =
                new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz", java.util.Locale.US);
        HttpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date httpDateTime = Calendar.getInstance().getTime();
        return HttpDateFormat.format(httpDateTime);
    }


    /**
     * generalheaderの部品を集めて組み立て生成
     */

    public String generateGeneralHeader() {

        StringBuilder sb = new StringBuilder();
        // sb.append("Cache-Control: " ).append("\n");
        // sb.append("Connection: " ).append("\n");
        sb.append("Date: " + generateHttpDateTime()).append("\n");
        // sb.append("Pragma: " ).append("\n");
        // sb.append("Trailer: " ).append("\n");
        // sb.append("Transfer-Encoding: " ).append("\n");
        // sb.append("Upgrade: " ).append("\n");
        // sb.append("Via: " ).append("\n");
        // sb.append("Warning: " ).append("\n");

        String generalHeader = new String(sb);
        return generalHeader;
    }


    /**
     * responseheaderの部品を集めて組み立て生成
     */

    public String generateResponseHeader() {

        StringBuilder sb = new StringBuilder();
        // sb.append("Accept-Ranges:" + "none" ).append("\n");
        // sb.append("Age: " ).append("\n");
        // sb.append("ETag: " ).append("\n");
        // sb.append("Location: " ).append("\n");
        // sb.append("Proxy-Authenticate: " ).append("\n");
        // sb.append("Retry-After:" ).append("\n");
        sb.append("Server: " + "sakura818").append("\n");
        // sb.append("Vary: " ).append("\n");
        // sb.append("WWW-Authenticate: " ).append("\n");


        String responseHeader = new String(sb);
        return responseHeader;
    }

    /**
     * entityheaderの部品を集めて組み立て生成
     */

    public String generateEntityHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Allow: " + "GET, HEAD").append("\n");
        // sb.append("Content-Encoding: " ).append("\n");
        sb.append("Content-Language: " + "ja, en").append("\n");
        sb.append("Content-Length: " + "3495").append("\n");// TODO: Content-Lengthを計算するメソッドを作成して呼び出す
        // sb.append("Content-Location:" ).append("\n");
        // sb.append("Content-MD5: " ).append("\n");
        // sb.append("Content-Range: ).append("\n");
        sb.append("Content-Type: " + extensionToContentType()).append("\n");
        // sb.append("Expires: " ).append("\n");
        // sb.append("Last-Modified: " ).append("\n");

        String entityHeader = new String(sb);
        return entityHeader;
    }

    /**
     * ResponseMessageHeaderを生成
     * ResponseMessageHeader = generalHeader + responseHeader + entityHeader
     */

    public String generateResponseMessageHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append(generateGeneralHeader());
        sb.append(generateResponseHeader());
        sb.append(generateEntityHeader());

        String responseMessageHeader = new String(sb);
        return responseMessageHeader;
    }


    /**
     * ResponseMessageBodyを生成
     */
    public String generateResponseMessageBody() {

        return "responseMessageBody";

    }


}

