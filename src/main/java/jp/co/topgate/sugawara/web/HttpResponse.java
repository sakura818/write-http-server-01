package jp.co.topgate.sugawara.web;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HttpResponse class
 * レスポンスを生成する
 * TODO:ネスト深すぎ　ファイルの読み込みがいまいちわかっていなさそう
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
     *
     */
    public void createHttpResponse(OutputStream outputStream, StatusCode statusCode) {
        // PrintWriter writer = new PrintWriter(outputStream, true);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + statusCode.mappingStatusCode()).append("\n");
        sb.append(createResponseMessageHeader()).append("\n");
        sb.append(createResponseMessageBody(statusCode)).append("\n");

    }


    /**
     * generalheaderに使われるメッセージが生成された日付・時刻を表すDate行を生成
     * RFC1123 の時刻フォーマット
     */
    public String createHttpDateTime() {
        SimpleDateFormat HttpDateFormat =
                new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss zzz", java.util.Locale.US);
        HttpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date httpDateTime = Calendar.getInstance().getTime();
        return HttpDateFormat.format(httpDateTime);
    }


    /**
     * generalheaderの部品を集めて組み立て生成
     */

    public String createGeneralHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Date: " + createHttpDateTime()).append("\n");

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
     *
     */

    public String createEntityHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("Allow: " + "GET, HEAD").append("\n");
        sb.append("Content-Language: " + "ja, en").append("\n");
        MIME mime = new MIME();
        sb.append(MIME.selectContentType(".html")).append("\n");

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


    /**
     * ResponseMessageBodyを生成
     */
    public String createResponseMessageBody(StatusCode statusCode) {

        return "responseMessageBody";

        if (this.responseMessageBodyFile != null) {
            switch (statusCode.getStatusCode()) {
                case 200:
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

                    return "200 Response Message Body";

                case 400:
                    String errorPageHtml400 = "<html><head><title>400 Bad Request</title></head>" +
                            "<body><h1>Bad Request</h1>" +
                            "<p>リクエストにエラーがあります。<br /></p></body></html>";
                    break;

                case 404:
                    String errorPageHtml404 = "<html><head><title>404 Not Found</title></head>" +
                            "<body><h1>Not Found</h1>" +
                            "<p>該当のページは見つかりませんでした。</p></body></html>";
                    break;

                default:
                    String errorPageHtmlDefault = "<html><head><title>500 Internal Server Error</title></head>" +
                            "<body><h1>Internal Server Error</h1>" +
                            "<p>サーバー内部のエラーにより表示できません。</p></body></html>";
            }
        }

    }
}

