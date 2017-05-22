package jp.co.topgate.sugawara.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * HttpResponseMessageHeaderContent Class
 * HttpResponseのMessageHeaderのContentを生成するクラス
 * MessageHeader = *((GeneralHeader | ResponseHeader | EntityHeader )CRLF)
 * HttpResponseのContentを3つに分けたうちの1つである。
 *
 * @author sakura818
 */

public class HttpResponseMessageHeaderContent {

    /**
     * ResponseMessageHeaderを生成する
     * ResponseMessageHeader = *((GeneralHeader | ResponseHeader | EntityHeader )CRLF)
     *
     * @param filePath ex:index.html
     * @return httpResponseMessageHeaderContent
     */

    public String createHttpResponseMessageHeader(File filePath) {
        StringBuilder httpResponseMessageHeader = new StringBuilder();
        httpResponseMessageHeader.append(createGeneralHeader());
        httpResponseMessageHeader.append(createResponseHeader());
        httpResponseMessageHeader.append(createEntityHeader(filePath));
        String httpResponseMessageHeaderContent = new String(httpResponseMessageHeader);
        return httpResponseMessageHeaderContent;
    }

    /**
     * GeneralHeaderを生成する
     * GeneralHeaderとは一般的な適用性を持つが、転送されたエンティティには適用されないヘッダ
     * Cache-ControlやDateなどを追加するときここに記述する
     * 今回は簡易的な機能のみを提供するためGeneralHeaderになにも記載していない
     *
     * @return generalHeaderContent
     */

    public String createGeneralHeader() {
        StringBuilder generalHeader = new StringBuilder();
        String generalHeaderContent = new String(generalHeader);
        return generalHeaderContent;
    }

    /**
     * ResponseHeaderを生成する
     * ResponseHeaderとはサーバについてや、Request-URI によって識別されるリソースへの更なるアクセスに関する情報を与える。
     *
     * @return responseHeaderContent ex:"Server: sakura818"
     */

    public String createResponseHeader() {
        StringBuilder responseHeader = new StringBuilder();
        responseHeader.append("Server: " + "sakura818").append("\n");
        String responseHeaderContent = new String(responseHeader);
        return responseHeaderContent;
    }

    /**
     * EntityHeaderを生成する
     * EntityHeaderとはエンティティボディや、もしボディが無ければリクエストによって識別されたリソースについての外部情報を定義する。
     *
     * @param filePath ex:index.html
     * @return entityHeaderContent
     */

    public String createEntityHeader(File filePath) {
        StringBuilder entityHeader = new StringBuilder();
        entityHeader.append("Allow: " + "GET").append("\n");
        entityHeader.append("Content-Language: " + "en").append("\n");
        entityHeader.append("Content-Type: " + createContentType(filePath)).append("\n");
        String entityHeaderContent = new String(entityHeader);
        return entityHeaderContent;
    }

    /**
     * ContentTypeを生成する
     * ContentTypeとは元のデータのメディアタイプ
     *
     * @param filePath ex:index.html
     * @return ContentType ex:text/html
     */

    public String createContentType(File filePath) {
        if (extensionToContentType.containsKey(extractExtension(filePath))) {
            return extensionToContentType.get(extractExtension(filePath));
        }
        return null;
    }

    /**
     * ファイルから拡張子を抜き出す。なぜならContentTypeはファイルの拡張子によって判別されるから。
     *
     * @param filePath ex:index.html
     * @return ファイルの拡張子　ex:html
     */

    public String extractExtension(File filePath) {
        if (filePath == null) {
            return null;
        }
        String fileName = filePath.getName();
        int lastDotPosition = fileName.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return fileName.substring(lastDotPosition + 1);
        } else
            return fileName.substring(lastDotPosition + 1);
    }

    /**
     * 拡張子とContentTypeのMap
     */

    public final Map<String, String> extensionToContentType = new HashMap<String, String>() {
        {
            put("html", "text/html; charset=UTF-8");
            put("htm", "text/html; charset=UTF-8");
            put("css", "text/css");
            put("js", "application/javascript");
            put("jpg", "image/jpeg");
            put("jpeg", "image/jpeg");
            put("png", "image/png");
            put("gif", "image/gif");
            put("txt", "text/plain");
            put("pdf", "application/pdf");
            put("mp4", "video/mp4");
        }
    };

}
