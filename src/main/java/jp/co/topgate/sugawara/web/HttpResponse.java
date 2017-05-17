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
     * 生成したレスポンスのコンテンツをOutputStreamに書き込む
     * 生成したレスポンスのコンテンツ
     * = HttpResponseStatusLineContent + HttpResponseMessageHeaderContent + HttpResponseBodyContent
     *
     * @param outputStream 書き込み先データストリーム
     * @throws IOException
     */
    public void writeResponseOutputStream(OutputStream outputStream, File filePath, int statusCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream);
        StringBuilder httpResponse = new StringBuilder();

        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent(statusCode);
        httpResponse.append(httpResponseStatusLineContent.createResponseStatusLine(statusCode)).append("\n");

        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent
                = new HttpResponseMessageHeaderContent(filePath);
        httpResponse.append(httpResponseMessageHeaderContent.createResponseMessageHeader(filePath)).append("\n");

        httpResponse.append("\n");

        HttpResponseMessageBodyContent httpResponseMessageBodyContent
                = new HttpResponseMessageBodyContent(filePath, statusCode);

        httpResponseMessageBodyContent.createResponseMessageBody(filePath,statusCode);
        responseBodyTextFile = httpResponseMessageBodyContent.getResponseBodyTextFile();

        if (responseBodyTextFile != null) {
            httpResponse.append(responseBodyTextFile).append("\n");
        }

        httpResponseMessageBodyContent.getResponseBodyBinaryFile();

        if (responseBodyBinaryFile != null) {
            BufferedInputStream bufferedInputStream
                    = new BufferedInputStream(new FileInputStream(responseBodyBinaryFile));
            try {
                int binaryData; // 読み込んだデータを格納
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
        }
        printWriter.write(httpResponse.toString());
    }

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


}



