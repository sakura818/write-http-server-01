package jp.co.topgate.sugawara.web;

import java.util.HashMap;
import java.util.Map;

/**
 * ContentType Class
 * ファイル名から拡張子を取得し、それに応じたContentTypeをかえす
 *
 * Created by haruka.sugawara on 2017/05/08.
 */
public class ContentType {
    private static final Map<String, String> CONTENT_TYPE = new HashMap<String, String>() {
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

    public static String determineFileExtension(String fileName) {
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
     * Content-Typeを返します.
     *
     * @param fileName ファイル名
     * @return CONTENT_TYPE
     */
    public static String determineContentType(String fileName) {
        fileName = determineFileExtension(fileName);
        if (fileName == null) {
            return null;
        }

        if (CONTENT_TYPE.containsKey(fileName)) {
            return CONTENT_TYPE.get(fileName);
        } else {
            // DefaultContentType
            return CONTENT_TYPE.get("octet-stream");
        }
    }


}
