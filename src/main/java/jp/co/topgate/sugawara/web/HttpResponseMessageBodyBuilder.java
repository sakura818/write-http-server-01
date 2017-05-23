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
    private int statusCode;

    public HttpResponseMessageBodyBuilder(File file, int statusCode){
        this.file = file;
        this.statusCode = statusCode;
    }

    /**
     * statusCodeで条件分岐した後、fileもしくはmessageBodyHtmlをバイト型の配列で読み込みHttpResponseMessageBodyを生成する
     *
     * @return messageBody
     */

    public byte[] build() throws IOException {
        byte[] messageBody = new byte[(int) this.file.length()];
        String messageBodyHtml;
        if (this.statusCode == 200) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.file));
            try {
                bufferedInputStream.read(messageBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedInputStream.close();
            }
        } else if (this.statusCode == 404) {
            messageBodyHtml = "<html><head><title>404 Not Found</title></head>" +
                    "<body><h1>Not Found</h1>" +
                    "<p>該当のページは見つかりませんでした。</p></body></html>";
            messageBody = messageBodyHtml.getBytes();
        }
        return messageBody;
    }
}
