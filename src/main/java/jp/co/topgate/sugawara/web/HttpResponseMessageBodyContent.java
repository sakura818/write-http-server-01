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
     * TODO:200のファイルを呼び出すところまだ書いていない　ファイルを呼び出すときにTextかBinaryか分ける
     *
     * @return ResponseMessageBody
     */


    // private static final String FILE_DIR = "src/main/resources/";
    // File file = new File(FILE_DIR, httpRequest.getFilePath());

    /**
     * ステータスコードに応じて適切なファイルやhtmlを返す
     */
    private String responseBodyTextFile;
    private String responseBodyBinaryFile;

    public String getResponseBodyTextFile() {
        return this.responseBodyTextFile;
    }

    public String getResponseBodyBinaryFile() {
        return this.responseBodyBinaryFile;
    }

    public String createResponseMessageBody(int statusCode, File file) {

        if (statusCode == 200) {
            // private static final String FILE_DIR = "src/main/resources/";
            // File file = new File(FILE_DIR, httpRequest.getFilePath());
            return "200 Response Message Body";
        } else if (statusCode == 400) {
            responseBodyTextFile = "<html><head><title>400 Bad Request</title></head>" +
                    "<body><h1>Bad Request</h1>" +
                    "<p>リクエストにエラーがあります。</p></body></html>";
            return responseBodyTextFile;
        } else if (statusCode == 404) {
            responseBodyTextFile = "<html><head><title>404 Not Found</title></head>" +
                    "<body><h1>Not Found</h1>" +
                    "<p>該当のページは見つかりませんでした。</p></body></html>";
            return responseBodyTextFile;
        }
        return responseBodyTextFile = "no content";
    }
}
