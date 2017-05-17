package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * HttpResponseMessageBodyContent Class
 * HttpResponseのMessageBodyのContentを生成するクラス
 * MessageBodyはファイルのデータの中身
 * HttpResponseのContentを3つに分けたうちの1つである。
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyContent {

    private String responseBodyTextFile;
    private String responseBodyBinaryFile;

    public String getResponseBodyTextFile() {
        return this.responseBodyTextFile;
    }

    public String getResponseBodyBinaryFile() {
        return this.responseBodyBinaryFile;
    }

    /*

    private File file;
    public HttpResponseMessageBodyContent(File filePath, int statusCode) {
        this.file = filePath;
    }
    */

    /**
     * ResponseMessageBodyを生成する
     * statusCodeとfilePathに応じてresponseBodyTextFileかresponseBodyBinaryFileをかえす
     * //TODO ファイルなのにString型で返すのはおかしい
     *
     * @param statusCode ex:200
     * @param filePath   ex:index.html
     * @return ResponseMessageBody
     */

    public String createResponseMessageBody(File filePath,int statusCode) throws IOException {
        if (statusCode == 200) {
            // 判別する方法は文字コード0があればバイナリファイル、なはればテキストファイル
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
