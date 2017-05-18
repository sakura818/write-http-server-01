package jp.co.topgate.sugawara.web;

import java.io.*;


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



    /**
     * filePathがresponseBodyTextFileかresponseBodyBinaryFileか判断する
     *
     * @param statusCode ex:200
     * @param filePath   ex:index.html
     * @return ResponseMessageBody
     */

    public byte[] createResponseMessageBody(File filePath, int statusCode) throws IOException {
        if (statusCode == 200) {
            BufferedInputStream bufferedInputStream
                    = new BufferedInputStream(new FileInputStream(filePath));
            try {
                int binaryData;
                while ((binaryData = bufferedInputStream.read()) != -1) {
                    outputStream.write(binaryData);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }

            return byte[];
        } else if (statusCode == 400) {
            responseBodyTextFile = "<html><head><title>400 Bad Request</title></head>" +
                    "<body><h1>Bad Request</h1>" +
                    "<p>リクエストにエラーがあります。</p></body></html>";
            return byte[];
        } else if (statusCode == 404) {
            responseBodyTextFile = "<html><head><title>404 Not Found</title></head>" +
                    "<body><h1>Not Found</h1>" +
                    "<p>該当のページは見つかりませんでした。</p></body></html>";
            return byte[];
        }
    }


}
