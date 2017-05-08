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
    private String responseMessageBodyText;
    private File responseMessageBodyFile;
    BufferedInputStream bis = null;

    private String statusLine;
    private String extension;


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
     * バイナリファイルを読み込んで表示するコード
     */
    public void generateHttpResponse(OutputStream outputStream, int statusCode) {
        PrintWriter writer = new PrintWriter(outputStream, true);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + statusCodeMap(statusCode)).append("\n");
        sb.append(generateResponseMessageHeader()).append("\n");

        if (this.responseMessageBodyFile != null) {
            try {
                FileInputStream fis = new FileInputStream(this.responseMessageBodyFile);
                bis = new BufferedInputStream(fis);

                int i = fis.read();
                {
                    while (i != -1) {
                        char c = (char) i;
                        System.out.println(c);
                        i = fis.read();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (bis != null) try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public String statusCodeMap(int status) {
        final Map<Integer, String> STATUS_CODE = new HashMap<Integer, String>() {
            {
                put(200, "OK");
                put(400, "Bad Request");
                put(404, "Not Found");
            }
        };
        if (STATUS_CODE.containsKey(status)) {
            return STATUS_CODE + " " + STATUS_CODE.get(status);
        } else {
            return STATUS_CODE + "Unknown";
        }
    }


    /**
     * ファイルの拡張子に対するcontent-typeの一覧表
     *
     * @param filename
     * @return content-type
     */
    public String contentTypeMap(String filename) {
        filename = determineFileExtension(filename);
        final Map<String, String> CONTENT_TYPE = new HashMap<String, String>() {
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

        if (CONTENT_TYPE.containsKey(extension)) {
            return "Content-Type: " + CONTENT_TYPE.get(extension);
        } else {
            return "Content-Type: " + "Unknown";
        }
    }


    /**
     * ファイルの拡張子を取得する
     *
     * @param filename
     * @return ファイルの拡張子
     */

    public String determineFileExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int lastDotPosition = filename.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return filename.substring(lastDotPosition + 1);
        }
        return null;
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
     * // TODO: Content-Lengthを計算するメソッドを作成して呼び出す
     */

    public String generateEntityHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Allow: " + "GET, HEAD").append("\n");
        sb.append("Content-Language: " + "ja, en").append("\n");
        // sb.append("Content-Length: " + "3495").append("\n");
        sb.append(contentTypeMap(getFilePath)).append("\n");

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

    /**
     * エラーのページを生成
     */


    private String generateResponseErrorMessageBody(int statusCode) {
        String errorPageHtml;
        switch (statusCode) {
            case 400:
                errorPageHtml = "<html><head><title>400 Bad Request</title></head>" +
                        "<body><h1>Bad Request</h1>" +
                        "<p>リクエストにエラーがあります。<br /></p></body></html>";
                break;

            case 404:
                errorPageHtml = "<html><head><title>404 Not Found</title></head>" +
                        "<body><h1>Not Found</h1>" +
                        "<p>該当のページは見つかりませんでした。</p></body></html>";
                break;

            default:
                errorPageHtml = "<html><head><title>500 Internal Server Error</title></head>" +
                        "<body><h1>Internal Server Error</h1>" +
                        "<p>サーバー内部のエラーにより表示できません。</p></body></html>";
        }
        return errorPageHtml;
    }

}

