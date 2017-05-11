package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpResponse class
 * レスポンスを出力ストリームOutputStreamに送信する
 * TODO:ファイルの読み込みがわかってない
 *
 * @author sakura818
 */
public class HttpResponse {

    private String responseBodyText;
    private File responseBodyFile;

    /**
     * テストのためにHTTPレスポンスボディを設定する
     *
     * @param text テキスト
     */
    public void setResponseBodyText(String text) {
        this.responseBodyText = text;
    }

    /**
     * テストのためにHTTPレスポンスボディにファイルを設定する
     *
     * @param file ファイル
     */
    public void setResponseBodyFile(File file) {
        this.responseBodyFile = file;
    }


    /**
     * 生成したレスポンスのコンテンツをOutputStreamに書き込む
     * 生成したレスポンスのコンテンツ　= HttpResponseStatusLineContent + HttpResponseMessageHeaderContent + HttpResponseBodyContent
     *
     * @param outputStream 書き込み先データストリーム
     * @param statusCode      ステータスクラス
     * @throws IOException 書き込みエラー
     */
    public void writeResponseOutputStream(OutputStream outputStream, int statusCode) throws IOException {
        PrintWriter writer = new PrintWriter(outputStream, true);
        HttpResponseStatusLineContent httpResponseStatusLineContent = new HttpResponseStatusLineContent();
        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent = new HttpResponseMessageHeaderContent();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpResponseStatusLineContent.getResponseStatusLineContent()).append("\n");
        stringBuilder.append(httpResponseMessageHeaderContent.getResponseMessageHeaderContent()).append("\n");

        if (this.responseBodyText != null) {
            stringBuilder.append("\n").append(this.responseBodyText + "\n");
        }
        writer.println(stringBuilder.toString());

        if (this.responseBodyFile != null) {
            BufferedInputStream bufferedInputStream
                    = new BufferedInputStream(new FileInputStream(this.responseBodyFile));
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



