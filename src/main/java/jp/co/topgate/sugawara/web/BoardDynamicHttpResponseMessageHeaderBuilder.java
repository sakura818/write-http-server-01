package jp.co.topgate.sugawara.web;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseMessageHeaderBuilder {
    // TODO:Staticと処理同じだから一緒にしたほうがいいのかもしれない(違うかもしれない)　今回たまたま処理を一緒にできるだけで他の動的アプリケーションの場合は一緒にできないかもしれないから処理を継承させずに独立させておくほうが無難?かもしれない

    private File file;

    /**
     * コンストラクタ
     *
     * @param file
     */

    public BoardDynamicHttpResponseMessageHeaderBuilder(File file) {
        this.file = file;
    }

    /**
     * MessageHeaderを生成する
     *
     * @return httpResponseMessageHeaderContent
     */

    public byte[] build() {
        StringBuilder messageHeader = new StringBuilder();
        messageHeader.append(createGeneralHeader());
        messageHeader.append(createResponseHeader());
        messageHeader.append(createEntityHeader(this.file)).append("\n");
        return (messageHeader.toString()).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * GeneralHeaderを生成する
     * GeneralHeaderとは一般的な適用性を持つが、転送されたエンティティには適用されないヘッダ
     * Cache-ControlやDateなどを追加するときここに記述する
     * 今回は簡易的な機能のみを提供するためGeneralHeaderになにも記載していない
     *
     * @return generalHeaderContent
     */

    String createGeneralHeader() {
        StringBuilder generalHeader = new StringBuilder();
        return generalHeader.toString();
    }

    /**
     * ResponseHeaderを生成する
     * ResponseHeaderとはサーバについてや、Request-URI によって識別されるリソースへの更なるアクセスに関する情報を与える。
     *
     * @return responseHeaderContent ex:"Server: sakura818"
     */

    String createResponseHeader() {
        StringBuilder responseHeader = new StringBuilder();
        responseHeader.append("Server: " + "sakura818").append("\n");
        return responseHeader.toString();
    }

    /**
     * EntityHeaderを生成する
     * EntityHeaderとはエンティティボディや、もしボディが無ければリクエストによって識別されたリソースについての外部情報を定義する。
     *
     * @param file ex:index.html
     * @return entityHeaderContent
     */

    String createEntityHeader(File file) {
        StringBuilder entityHeader = new StringBuilder();
        entityHeader.append("Allow: " + "GET").append("\n");
        entityHeader.append("Content-Language: " + "en").append("\n");
        entityHeader.append("Content-Type: " + catchContentType(file)).append("\n");
        return entityHeader.toString();
    }

    /**
     * ContentTypeを生成する
     * ContentTypeとは元のデータのメディアタイプ
     *
     * @param file ex:index.html
     * @return ContentType ex:text/html
     */

    String catchContentType(File file) {
        if (extensionToContentType.containsKey(extractExtension(file))) {
            return extensionToContentType.get(extractExtension(file));
        }
        return "text/html; charset=utf-8";
    }

    /**
     * ファイルから拡張子を抜き出す。ContentTypeはファイルの拡張子によって判別される。
     *
     * @param file ex:index.html
     * @return ファイルの拡張子　ex:html
     */

    String extractExtension(File file) throws IndexOutOfBoundsException {
        String fileName = file.getName();
        int lastDotPosition;
        lastDotPosition = fileName.lastIndexOf(".");
        if (lastDotPosition > 0) {
            String extension = fileName.substring(lastDotPosition + 1);
            return extension;
        }
        return fileName;
    }

    /**
     * 拡張子とContentTypeのMap
     */

    final Map<String, String> extensionToContentType = new HashMap<String, String>() {
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
