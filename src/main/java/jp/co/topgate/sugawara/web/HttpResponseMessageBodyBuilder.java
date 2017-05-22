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
     * @param file   ex:index.html
     * @param statusCode ex:200
     * @return messageBody
     */

    public byte[] build(File file, int statusCode) throws IOException {
        byte[] messageBody = new byte[(int) file.length()];
        String messageBodyHtml;
        if (statusCode == 200) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                bufferedInputStream.read(messageBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                bufferedInputStream.close();
            }
        } else if (statusCode == 400) {
            messageBodyHtml = "<html><head><title>400 Bad Request</title></head>" +
                    "<body><h1>Bad Request</h1>" +
                    "<p>リクエストにエラーがあります。</p></body></html>";
            messageBody = messageBodyHtml.getBytes();
        } else if (statusCode == 404) {
            messageBodyHtml = "<html><head><title>404 Not Found</title></head>" +
                    "<body><h1>Not Found</h1>" +
                    "<p>該当のページは見つかりませんでした。</p></body></html>";
            messageBody = messageBodyHtml.getBytes();
        }
        return messageBody;
    }
}
