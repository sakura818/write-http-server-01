package jp.co.topgate.sugawara.web;

import java.io.*;

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
     * テストのためにレスポンスボディを設定する
     *
     * @param text ex:index.html
     */
    public void setResponseBodyText(String text) {
        this.responseBodyTextFile = text;
    }

    /**
     * テストのためにレスポンスボディにファイルを設定する
     *
     * @param file ex:cream.png
     */
    public void setResponseBodyFile(File file) {
        this.responseBodyBinaryFile = file;
    }

    private StringBuilder httpResponse = new StringBuilder();


    /**
     * 生成したレスポンスのコンテンツをOutputStreamに書き込む
     * 生成したレスポンスのコンテンツ　= HttpResponseStatusLineContent + HttpResponseMessageHeaderContent + HttpResponseBodyContent
     *
     * @param outputStream 書き込み先データストリーム
     * @throws IOException
     */
    public void writeResponseOutputStream(OutputStream outputStream, File file, int statusCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent(statusCode);
        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent = new HttpResponseMessageHeaderContent(file);
        HttpResponseMessageBodyContent httpResponseMessageBodyContent = new HttpResponseMessageBodyContent(file, statusCode);

        StringBuilder httpResponse = new StringBuilder();
        httpResponse.append(httpResponseStatusLineContent.getResponseStatusLineContent()).append("\n");
        httpResponse.append(httpResponseMessageHeaderContent.getResponseMessageHeaderContent()).append("\n");
        httpResponseMessageBodyContent.getResponseBodyTextFile();

        if (responseBodyTextFile != null) {
            httpResponse.append(responseBodyTextFile).append("\n");
        }
        printWriter.println(httpResponse.toString());

        httpResponseMessageBodyContent.getResponseBodyBinaryFile();

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

    /**
     * HttpResponseを取得する
     *
     * @return httpResponse
     */
    public String getHttpResponse() {
        return httpResponse.toString();
    }

}



