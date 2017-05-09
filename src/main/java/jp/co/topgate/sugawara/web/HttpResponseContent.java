package jp.co.topgate.sugawara.web;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 *
 * レスポンスのコンテンツを生成するクラス
 *
 * @author sakura818
 *
 */

public class HttpResponseContent {


    /**
     * レスポンスの部品を集めて組み立て生成
     */
    public void createHttpResponse(OutputStream outputStream, int currentStatusCode) {
        // PrintWriter writer = new PrintWriter(outputStream, true);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1" + " " + statusCode.mappingStatusCode(currentStatusCode)).append("\n");
        sb.append(createResponseMessageHeader()).append("\n");
        sb.append(createResponseMessageBody(statusCode)).append("\n");

    }


    private static final Map<String, String> parseExtension = new HashMap<String, String>() {
        {
            put("html", "text/html");
            put("htm", "text/html");
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
     * ファイルから拡張子を取得する
     *
     * @param file
     * @return ファイルの拡張子
     */

    public static String parseFileExtension(String file) {
        if (file == null) {
            return null;
        }
        int lastDotPosition = file.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return file.substring(lastDotPosition + 1);
        }
        return null;
    }

    /**
     * ファイルの拡張子に対応するContentTypeを返す
     * DefaultMIME = octet-stream
     *
     * @param fileName ファイル名
     * @return CONTENT_TYPE
     */

    public static String selectContentType(String fileName) {
        String fileExtension = partFileExtension(fileName);
        if (fileExtension == null) {
            return null;
        }
        if (mapMIME.containsKey(fileExtension)) {
            return mapMIME.get(fileExtension);
        } else {
            return mapMIME.get("octet-stream");
        }
    }



    /**
     * generalheaderの部品を集めて組み立て生成
     * 今回はなにもここに追加しないが、本当はCache-ControlやDateなどがある
     */

    public String createGeneralHeader() {

        StringBuilder sb = new StringBuilder();
        String generalHeader = new String(sb);
        return generalHeader;
    }


    /**
     * responseheaderの部品を集めて組み立て生成
     */

    public String createResponseHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Server: " + "sakura818").append("\n");

        String responseHeader = new String(sb);
        return responseHeader;
    }

    /**
     * entityheaderの部品を集めて組み立て生成
     */

    public String createEntityHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Allow: " + "GET, HEAD").append("\n");
        sb.append("Content-Language: " + "ja, en").append("\n");
        sb.append(ContentType.selectContentType(".html")).append("\n");

        String entityHeader = new String(sb);
        return entityHeader;
    }

    /**
     * ResponseMessageHeaderを生成
     * ResponseMessageHeader = generalHeader + responseHeader + entityHeader
     */

    public String createResponseMessageHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append(createGeneralHeader());
        sb.append(createResponseHeader());
        sb.append(createEntityHeader());

        String responseMessageHeader = new String(sb);
        return responseMessageHeader;
    }

    public String statusCode200(int currentStatusCode) {
        if (statusCode.getStatusCode() == 200) {
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
        return "200 Response Message Body";
    }

    public String statusCode400(int currentStatusCode) {
        if (statusCode.getStatusCode() == 400) {
        }
        String errorPageHtml400 = "<html><head><title>400 Bad Request</title></head>" +
                "<body><h1>Bad Request</h1>" +
                "<p>リクエストにエラーがあります。</p></body></html>";
        return errorPageHtml400;
    }

    public String statusCode404(int currentStatusCode) {
        if (statusCode.getStatusCode() == 404) {}
        String errorPageHtml404 = "<html><head><title>404 Not Found</title></head>" +
                "<body><h1>Not Found</h1>" +
                "<p>該当のページは見つかりませんでした。</p></body></html>";
        return errorPageHtml404;
    }


    /**
     * ResponseMessageBodyを生成
     */
    public String createResponseMessageBody(StatusCode statusCode) {

        return "responseMessageBody";

    }

}
