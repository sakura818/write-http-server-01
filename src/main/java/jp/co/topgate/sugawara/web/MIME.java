package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * MIME Class
 * MIME ファイル名から拡張子を取得し、それに応じたContentTypeをかえす
 * <p>
 * Created by haruka.sugawara on 2017/05/08.
 */
public class MIME {
    String fileExtension;
    private static final Map<String, String> mapMIME = new HashMap<String, String>() {
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
     *
     * @param fileName
     * @return ファイルの拡張子
     */

    public String partFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int lastDotPosition = fileName.lastIndexOf(".");
        if (lastDotPosition != -1) {
            fileExtension = fileName.substring(lastDotPosition + 1);
            return fileExtension;
        }
        return null;
    }

    /**
     * ファイルの拡張子に対応するContentTypeを返す
     *
     * @param fileName ファイル名
     * @return CONTENT_TYPE
     */
    public String determineContentType(String fileName) {
        fileExtension = partFileExtension(fileName);
        if (fileExtension == null) {
            return null;
        }
        if (mapMIME.containsKey(fileExtension)) {
            return mapMIME.get(fileExtension);
        } else {
            // DefaultMIME
            return mapMIME.get("octet-stream");
        }
    }


}
