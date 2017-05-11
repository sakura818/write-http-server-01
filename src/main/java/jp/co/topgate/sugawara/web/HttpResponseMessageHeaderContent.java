package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;


/**
 * HttpResponseMessageHeaderContent Class
 * HttpResponseのMessageHeaderを生成する
 * MessageHeader = *((GeneralHeader | ResponseHeader | EntityHeader )CRLF)
 * HttpResponseのContentを3つに分けたうちの1つである。
 *
 * @author sakura818
 */

public class HttpResponseMessageHeaderContent {
    private String responseMessageHeaderContent;

    public String getResponseMessageHeaderContent() {
        return this.responseMessageHeaderContent;
    }

    /**
     * ResponseMessageHeaderを生成する
     * ResponseMessageHeader = *((GeneralHeader | ResponseHeader | EntityHeader )CRLF)
     *
     * @param file index.html
     * @return responseMessageHeaderContent
     */

    public String createResponseMessageHeader(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createGeneralHeader());
        stringBuilder.append(createResponseHeader());
        stringBuilder.append(createEntityHeader(file));
        String responseMessageHeaderContent = new String(stringBuilder);
        return responseMessageHeaderContent;
    }


    /**
     * GeneralHeaderを生成する
     * GeneralHeaderとは一般的な適用性を持つが、転送されたエンティティには適用されないヘッダ
     * Cache-ControlやDateなどを追加するときここに記述する
     * 今回は簡易的な機能のみを提供するためGeneralHeaderになにも記載していない
     * @return generalHeader
     */

    public String createGeneralHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        String generalHeaderContent = new String(stringBuilder);
        return generalHeaderContent;
    }

    /**
     * ResponseHeaderを生成する
     * ResponseHeaderとはサーバについてや、Request-URI によって識別されるリソースへの更なるアクセスに関する情報を与える。
     *
     * @return ResponseHeader ex:"Server: sakura818"
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
     *
     * @param file ex:index.html
     * @return EntityHeader
     */

    public String createEntityHeader(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Allow: " + "GET").append("\n");
        stringBuilder.append("Content-Language: " + "en").append("\n");
        stringBuilder.append(createContentType(file)).append("\n");
        String entityHeaderContent = new String(stringBuilder);
        return entityHeaderContent;
    }

    /**
     * ContentTypeを生成する
     * ContentTypeとは元のデータのメディアタイプ
     *
     * @param file ex:index.html
     * @return ContentType ex:text/html
     */

    public String createContentType(String file) {
        if (extensionToContentType.containsKey(extractExtension(file))) {
            return extensionToContentType.get(extractExtension(file));
        }
        return null;
    }

    /**
     * ファイルから拡張子を抜き出す。なぜならContentTypeはファイルの拡張子によって判別されるから。
     *
     * @param file ex:index.html
     * @return ファイルの拡張子　ex:html
     */

    public String extractExtension(String file) {
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
     * 拡張子とContentTypeのMap
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

}
