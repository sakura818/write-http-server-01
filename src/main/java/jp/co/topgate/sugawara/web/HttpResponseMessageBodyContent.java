package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * HttpResponseMessageBodyContent Class
 * HttpResponseのMessageBodyを生成する
 * MessageBodyはファイルのデータの中身
 * HttpResponseのContentを3つに分けたうちの1つである。
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyContent {

    /**
     * ResponseMessageBodyを生成する
     * TODO:メモに記載
     *
     * @return ResponseMessageBody
     */

    public String createResponseMessageBody() {
        return "responseMessageBody";
    }


    /**
     * statusCodeとreasonPhraseのMap
     */

    private static final Map<Integer, String> statusCodeToReasonPhrase = new HashMap<Integer, String>() {
        {
            put(200, "OK");
            put(400, "Bad Request");
            put(404, "Not Found");
        }
    };


    // private static final String FILE_DIR = "src/main/resources/";
    // File file = new File(FILE_DIR, httpRequest.getFilePath());

    /**
     * ステータスコードに応じて適切なファイルやhtmlを返す
     * 名前がひどい　分け方よくない
     * TODO:
     *
     */


    public String readBinaryFile(int statusCode, File file) {
        FileInputStream fileInputStream = null;

        responseBodyTextFile;
        responseBodyBinaryFile;


        /*

        バイト型のファイルを読み込み、それをStringBuilderにいれるためString型に変換する。
        1. FileInputStreamでバイト型のファイルを読み込む
        2. 1で生成したバイト型のファイルをString型に変換する .toCharArray();
        */

        if (statusCode == 200) {

        }
        return "200 Response Message Body";
    }

    public String badRequest(int statusCode) {
        if (statusCode == 400) {
        }
        String errorPageHtml400 = "<html><head><title>400 Bad Request</title></head>" +
                "<body><h1>Bad Request</h1>" +
                "<p>リクエストにエラーがあります。</p></body></html>";
        return errorPageHtml400;
    }

    public String notFound(int statusCode) {
        if (statusCode == 404) {
        }
        String errorPageHtml404 = "<html><head><title>404 Not Found</title></head>" +
                "<body><h1>Not Found</h1>" +
                "<p>該当のページは見つかりませんでした。</p></body></html>";
        return errorPageHtml404;
    }
}
