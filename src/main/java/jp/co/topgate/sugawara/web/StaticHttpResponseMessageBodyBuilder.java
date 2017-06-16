package jp.co.topgate.sugawara.web;

import java.io.*;


/**
 * StaticHttpResponseMessageBodyBuilder Class
 * HttpResponseのMessageBodyのContentを生成するクラス
 *
 * @author sakura818
 */

public class StaticHttpResponseMessageBodyBuilder {

    private File file;

    /**
     * コンストラクタ
     *
     * @param file
     */

    public StaticHttpResponseMessageBodyBuilder(File file) {
        this.file = file;
    }

    /**
     * fileをバイト型の配列で読み込みMessageBodyを生成する
     *
     * @return messageBody
     */

    public byte[] build() throws IOException {
        byte[] messageBody = new byte[(int) this.file.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.file));
        bufferedInputStream.read(messageBody);
        bufferedInputStream.close();

        return messageBody;
    }
}
