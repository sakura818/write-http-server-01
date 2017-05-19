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

    private String responseBodyTextFile;


    /**
     * ResponseMessageBodyを生成するためにfilePathからファイルをバイト型の配列で読み込む
     *
     * @param statusCode ex:200
     * @param filePath   ex:index.html
     * @return ResponseMessageBody
     */

    public byte[] createResponseMessageBody(File filePath, int statusCode) throws IOException {
        byte[] binaryData = new byte[500000];
        if (statusCode == 200) {
            BufferedInputStream bufferedInputStream
                    = new BufferedInputStream(new FileInputStream(filePath));
            //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
            try {
                bufferedInputStream.read(binaryData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedInputStream.close();
            }
            return binaryData;

        } else if (statusCode == 400) {
            responseBodyTextFile = "<html><head><title>400 Bad Request</title></head>" +
                    "<body><h1>Bad Request</h1>" +
                    "<p>リクエストにエラーがあります。</p></body></html>";
            binaryData = responseBodyTextFile.getBytes();
            return binaryData;
        } else if (statusCode == 404) {
            responseBodyTextFile = "<html><head><title>404 Not Found</title></head>" +
                    "<body><h1>Not Found</h1>" +
                    "<p>該当のページは見つかりませんでした。</p></body></html>";
            binaryData = responseBodyTextFile.getBytes();
            return binaryData;
        }
        return binaryData;
    }
}
