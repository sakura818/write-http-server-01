package jp.co.topgate.sugawara.web;

import java.io.*;


/**
 * HttpResponseMessageBodyBuilder Class
 * HttpResponseのMessageBodyのContentを生成するクラス
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyBuilder {

    private File filePath;

    public HttpResponseMessageBodyBuilder(File filePath) {
        this.filePath = filePath;
    }

    /**
     * filePathをバイト型の配列で読み込みHttpResponseMessageBodyを生成する
     *
     * @return messageBody
     */

    public byte[] build() throws IOException {
        byte[] messageBody = new byte[(int) this.filePath.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.filePath));
        try {
            bufferedInputStream.read(messageBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bufferedInputStream.close();
        }
        return messageBody;
    }
}
