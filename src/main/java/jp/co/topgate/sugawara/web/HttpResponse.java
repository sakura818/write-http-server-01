package jp.co.topgate.sugawara.web;

import java.io.*;
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

    private String statusLine;
    private String extension;


    private String responseMessageBodyText;
    private File responseMessageBodyFile;


    public String getFilepath() {
        return this.filepath;
    }

    public String getStatusLine() {
        return this.statusLine;
    }

    public void setResponseMessageBody(String text) {
        this.responseMessageBodyText = text;
    }

    public void setResponseMessageBodyFile(File file) {
        this.responseMessageBodyFile = file;
    }

    /**
     * レスポンスの部品を集めて組み立て生成
     */

    /*
    public void generateHttpResponse(String statusLine) throws IOException {

        String httpResponseData;
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.requestLineDivide(statusLine);

        StringBuilder sb = new StringBuilder();

        sb.append(responseLine()).append("\n");
        sb.append(generateResponseMessageHeader()).append("\n");
        if (


        if (httpRequest.getMethod() == "GET") {
            sb.append(generateResponseMessageBody());
        }

        System.out.println("response...");
        System.out.println(sb.toString());

    }


    /**
     * Java入門実践編　p257,p263を参考にた
     * バイナリファイルを読み込んで表示するコード
     */

    public void generateHttpResponse(OutputStream outputStream, int getStatusCode) {
        PrintWriter writer = new PrintWriter(outputStream, true);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + "statuscodeをよびだす" + " reason-phraseをよびだす").append("\n");
        sb.append ("generateResponseMessageHeaderをよびだす").append("¥n");


        System.out.println("バイナリファイルのすべてのデータを一文字ずつ読んで表示します" + "¥n");
        if (this.responseMessageBodyFile ! = null){
            FileInputStream fis = new FileInputStream(this, responseMessageBodyFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            try {
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
                throw new RuntimeException(e);
            } finally {
                if (bis != null) {
                    bis.close();
                }

            }
        }

        /**
         * ファイルの拡張子に対するcontent-typeの一覧表
         */

        public final Map<String, String> CONTENT_TYPE = new HashMap<String, String>() {
            {

                put("html", "text/html; charset=utf-8");
                put("htm", "text/html; charset=utf-8");
                put("css", "text/css");
                put("js", "application/javascript");
                put("jpg", "image/jpeg");
                put("jpeg", "image/jpeg");
                put("png", "image/png");
                put("gif", "image/gif");
                put("txt", "text/plain");
                put("pdf", "application/pdf");
                put("mp4", "video/mp4");
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
        sb.append("Date: " + generateHttpDateTime()).append("\n");

        String generalHeader = new String(sb);
        return generalHeader;
    }


    /**
     * responseheaderの部品を集めて組み立て生成
     */

    public String generateResponseHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Server: " + "sakura818").append("\n");

        String responseHeader = new String(sb);
        return responseHeader;
    }

    /**
     * entityheaderの部品を集めて組み立て生成
     * TODO: Content-Lengthを計算するメソッドを作成して呼び出す
     */

    public String generateEntityHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Allow: " + "GET, HEAD").append("\n");
        sb.append("Content-Language: " + "ja, en").append("\n");
        sb.append("Content-Length: " + "3495").append("\n");
        sb.append("Content-Type: " + extensionToContentType()).append("\n");

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

