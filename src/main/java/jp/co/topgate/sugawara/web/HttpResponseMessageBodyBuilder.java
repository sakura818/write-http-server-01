package jp.co.topgate.sugawara.web;

import java.io.*;


/**
 * HttpResponseMessageBodyBuilder Class
 * HttpResponseのMessageBodyのContentを生成するクラス
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyBuilder {

    private File file;

    public HttpResponseMessageBodyBuilder(File file) {
        this.file = file;
    }

    /**
     * fileをバイト型の配列で読み込みHttpResponseMessageBodyを生成する
     *
     * @return messageBody
     */

    public byte[] build() throws IOException {
        byte[] messageBody = new byte[(int) this.file.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.file));
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
