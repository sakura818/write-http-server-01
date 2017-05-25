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
    private int statusCode;

    public HttpResponseMessageBodyBuilder(File filePath, int statusCode) {
        this.filePath = filePath;
        this.statusCode = statusCode;
    }

    /**
     * statusCodeで条件分岐した後、filePathもしくはmessageBodyHtmlをバイト型の配列で読み込みHttpResponseMessageBodyを生成する
     *
     * @return messageBody
     */

    public byte[] build() throws IOException {
        byte[] messageBody = new byte[(int) this.filePath.length()];
        if (this.statusCode == 200) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.filePath));
            try {
                bufferedInputStream.read(messageBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedInputStream.close();
            }
        } else if (this.statusCode == 404) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.filePath));
            try {
                bufferedInputStream.read(messageBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedInputStream.close();
            }
        }
        return messageBody;
    }
}
