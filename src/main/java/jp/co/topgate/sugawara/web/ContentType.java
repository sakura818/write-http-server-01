package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * MIME Class
 * ファイル名から拡張子を取得しContentTypeを返す
 *
 * @author sakura818
 * TODO:rename
 */

public class MIME {
    private static final Map<String, String> mapMIME = new HashMap<String, String>() {
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
     * ファイルの拡張子を取得する
     *
     * @param fileName
     * @return ファイルの拡張子
     */

    public static String partFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int lastDotPosition = fileName.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return fileName.substring(lastDotPosition + 1);
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

}
