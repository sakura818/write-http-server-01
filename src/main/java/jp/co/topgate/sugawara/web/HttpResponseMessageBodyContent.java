package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


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


    private String responseBodyTextFile;
    private String responseBodyBinaryFile;

    public HttpResponseMessageBodyContent(File filePath, int statusCode) {
    }

    public String getResponseBodyTextFile() {
        return this.responseBodyTextFile;
    }

    public String getResponseBodyBinaryFile() {
        return this.responseBodyBinaryFile;
    }


    /**
     * statusCodeとfilePathに応じてresponseBodyTextFileかresponseBodyBinaryFileをかえす
     */

    public String createResponseMessageBody(int statusCode, File filePath) throws IOException {

        if (statusCode == 200) {
            // private static final String FILE_DIR = "src/main/resources/";
            // File filePath = new File(FILE_DIR, httpRequest.getFile());
            // TODO: ファイルがTextFileかBinaryFileか判別する
            // 判別する方法は文字コード00があればバイナリファイル、文字コード00がない場合はテキストファイル
            FileInputStream fileInputStream = new FileInputStream(filePath);

            byte[] b = new byte[1];
            while (fileInputStream.read(b, 0, 1) > 0) {
                if (b[0] == 0) {
                    return responseBodyBinaryFile;
                }
            }
            return responseBodyTextFile;
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
