package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpResponse class
 * HttpResponseを出力ストリームOutputStreamに送信する
 * HttpResponse = HttpResponseStatusLineContent Class
 * + HttpResponseMessageHeaderContent Class
 * + HttpResponseBodyContent Class
 *
 * @author sakura818
 */

public class HttpResponse {

    private String responseBodyTextFile;
    private File responseBodyBinaryFile;

    /**
     * テストのためにHTTPレスポンスボディを設定する
     *
     * @param text ex:index.html
     */
    public void setResponseBodyText(String text) {
        this.responseBodyTextFile = text;
    }

    /**
     * テストのためにHTTPレスポンスボディにファイルを設定する
     *
     * @param file ex:cream.png
     */
    public void setResponseBodyFile(File file) {
        this.responseBodyBinaryFile = file;
    }


    /**
     * 生成したレスポンスのコンテンツをOutputStreamに書き込む
     * 生成したレスポンスのコンテンツ　= HttpResponseStatusLineContent + HttpResponseMessageHeaderContent + HttpResponseBodyContent
     *
     * @param outputStream 書き込み先データストリーム
     * @param statusCode   ex:200
     * @throws IOException
     */
    public void writeResponseOutputStream(OutputStream outputStream, int statusCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent();
        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent = new HttpResponseMessageHeaderContent();
        HttpResponseMessageBodyContent httpResponseMessageBodyContent = new HttpResponseMessageBodyContent();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpResponseStatusLineContent.getResponseStatusLineContent()).append("\n");
        stringBuilder.append(httpResponseMessageHeaderContent.getResponseMessageHeaderContent()).append("\n");

        if (responseBodyTextFile != null) {
            stringBuilder.append(responseBodyTextFile).append("\n");
        }
        printWriter.println(stringBuilder.toString());

        if (responseBodyBinaryFile != null) {
            BufferedInputStream bufferedInputStream
                    = new BufferedInputStream(new FileInputStream(responseBodyBinaryFile));
            try {
                for (int c = bufferedInputStream.read(); c >= 0; c = bufferedInputStream.read()) {
                    outputStream.write(c);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
        }
    }
}



