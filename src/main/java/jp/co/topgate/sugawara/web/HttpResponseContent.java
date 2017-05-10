package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.*;

/**
 * レスポンスのコンテンツを生成するクラス
 *
 * @author sakura818
 */

public class HttpResponseContent {

    /**
     * レスポンスの部品を集めて組み立て生成
     * response = createResponseStatusLine + createResponseMessageHeader + createResponseMessageBody
     */
    public String createHttpResponseContent(int statusCode, String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createResponseStatusLine(statusCode)).append("\n");
        stringBuilder.append(createResponseMessageHeader(filePath)).append("\n");
        stringBuilder.append(createResponseMessageBody()).append("\n");
        String httpResponseContent = new String(stringBuilder);
        return httpResponseContent;
    }

    /**
     * ResponseStatusLineを生成する
     * Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
     */

    public String createResponseStatusLine(int statusCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1").append(" ");
        stringBuilder.append(statusCode).append(" ");
        stringBuilder.append(getReasonPhrase());
        String responseStatusLineContent = new String(stringBuilder);
        return responseStatusLineContent;
    }


    /**
     * ResponseMessageHeaderを生成する
     * ResponseMessageHeader = *((GeneralHeader | ResponseHeader | EntityHeader )CRLF)
     */

    public String createResponseMessageHeader(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createGeneralHeader());
        stringBuilder.append(createResponseHeader());
        stringBuilder.append(createEntityHeader(filePath));
        String responseMessageHeaderContent = new String(stringBuilder);
        return responseMessageHeaderContent;
    }

    /**
     * GeneralHeaderを生成する
     * GeneralHeaderとは一般的な適用性を持つが、転送されたエンティティには適用されないヘッダ
     * 今回はなにもここに追加しないが、Cache-ControlやDateなどを追加するときここに記述する
     */

    public String createGeneralHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        String generalHeaderContent = new String(stringBuilder);
        return generalHeaderContent;
    }

    /**
     * ResponseHeaderを生成する
     * ResponseHeaderとはサーバについてや、Request-URI によって識別されるリソースへの更なるアクセスに関する情報を与える。
     */

    public String createResponseHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Server: " + "sakura818").append("\n");
        String responseHeaderContent = new String(stringBuilder);
        return responseHeaderContent;
    }

    /**
     * EntityHeaderを生成する
     * EntityHeaderとはエンティティボディや、もしボディが無ければリクエストによって識別されたリソースについての外部情報を定義する。
     */

    public String createEntityHeader(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Allow: " + "GET").append("\n");
        stringBuilder.append("Content-Language: " + "en").append("\n");
        stringBuilder.append(createContentType(filePath)).append("\n");
        String entityHeaderContent = new String(stringBuilder);
        return entityHeaderContent;
    }

    /**
     * ContentTypeを生成する
     * ContentTypeとは元のデータのメディアタイプ
     *
     * @param filePath
     * @return ContentType
     */

    public String createContentType(String filePath) {
        if (extensionToContentType.containsKey(extractExtension(filePath))) {
            return extensionToContentType.get(extractExtension(filePath));
        }
        return null;
    }

    /**
     * ファイルパスから拡張子を抜き出す。なぜならContentTypeはファイルパスの拡張子によって判別されるから。
     *
     * @param filePath
     * @return ファイルパスの拡張子　例えばhtmlやtxt
     */

    public static String extractExtension(String filePath) {
        if (filePath == null) {
            return null;
        }
        int lastDotPosition = filePath.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return filePath.substring(lastDotPosition + 1);
        }
        return null;
    }

    /**
     * 拡張子とContentTypeの写像
     */
    private static final Map<String, String> extensionToContentType = new HashMap<String, String>() {
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
     * ResponseMessageBodyを生成する
     */

    public String createResponseMessageBody() {
        return "responseMessageBody";
    }

    private int statusCode;
    private String reasonPhrase;
    private String statusCodeAndReasonPhrase;

    /**
     * statusCodeとreasonPhraseの写像
     */

    private static final Map<Integer, String> statusCodeToReasonPhrase = new HashMap<Integer, String>() {
        {
            put(200, "OK");
            put(400, "Bad Request");
            put(404, "Not Found");
        }
    };


    /**
     * テストのためにステータスコードを設定する
     *
     * @param i ステータスコード　例えば200
     */

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    /**
     * テストのために現在設定されているstatusCodeを取得する
     *
     * @return statusCode
     */

    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * テストのために現在設定されているreasonPhraseを取得する。
     *
     * @return reasonPhrase
     */

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    /**
     * テストのために現在設定されているstatusCodeとreasonPhraseを取得する
     *
     * @return statusCode and reasonPhrase
     */


    /**
     * リクエストに対応して適切なステータスコードを返す
     */

    public int selectStatusCode(String method, File filePath) {
        int statusCode;
        if (method == null) {
            statusCode = 400;
            return statusCode;
        }
        if (!filePath.exists()) {
            statusCode = 404;
            return statusCode;
        }
        statusCode = 200;
        return statusCode;
    }


    private static final String FILE_DIR = "src/main/resources/";
    File file = new File(FILE_DIR, httpRequest.getFilePath());


    public String statusCode200(int statusCode) {
        if (statusCode == 200) {
            try {
                FileInputStream fis = new FileInputStream(this.responseMessageBodyFile);
                BufferedInputStream bis = new BufferedInputStream(fis);

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

    public String statusCode400(int statusCode) {
        if (statusCode == 400) {
        }
        String errorPageHtml400 = "<html><head><title>400 Bad Request</title></head>" +
                "<body><h1>Bad Request</h1>" +
                "<p>リクエストにエラーがあります。</p></body></html>";
        return errorPageHtml400;
    }

    public String statusCode404(int statusCode) {
        if (statusCode == 404) {
        }
        String errorPageHtml404 = "<html><head><title>404 Not Found</title></head>" +
                "<body><h1>Not Found</h1>" +
                "<p>該当のページは見つかりませんでした。</p></body></html>";
        return errorPageHtml404;
    }


}
