package jp.co.topgate.sugawara.web;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * HttpResponseMessageBodyContent Class
 * HttpResponseのMessageBodyのContentを生成するクラス
 * MessageBodyはファイルのデータの中身
 * HttpResponseのContentを3つに分けたうちの1つである。
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyContent {

    private String httpResponseMessageBodyHtml;
    
    /**
     * ResponseMessageBodyを生成するためにfilePathからファイルをバイト型の配列で読み込む
     *
     * @param statusCode ex:200
     * @param filePath   ex:index.html
     * @return ResponseMessageBody
     */

    public byte[] createHttpResponseMessageBody(File filePath, int statusCode) throws IOException {
        byte[] httpResponseMessageBodyContent = new byte[(int) filePath.length()];
        if (statusCode == 200) {
            BufferedInputStream bufferedInputStream
                    = new BufferedInputStream(new FileInputStream(filePath));
            try {
                bufferedInputStream.read(httpResponseMessageBodyContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedInputStream.close();
            }
        } else if (statusCode == 400) {
            httpResponseMessageBodyHtml = "<html><head><title>400 Bad Request</title></head>" +
                    "<body><h1>Bad Request</h1>" +
                    "<p>リクエストにエラーがあります。</p></body></html>";
            httpResponseMessageBodyContent = httpResponseMessageBodyHtml.getBytes();
            return httpResponseMessageBodyContent;
        } else if (statusCode == 404) {
            httpResponseMessageBodyHtml = "<html><head><title>404 Not Found</title></head>" +
                    "<body><h1>Not Found</h1>" +
                    "<p>該当のページは見つかりませんでした。</p></body></html>";
            httpResponseMessageBodyContent = httpResponseMessageBodyHtml.getBytes();
            return httpResponseMessageBodyContent;
        }
        return httpResponseMessageBodyContent;
    }
}
