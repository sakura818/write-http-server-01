package jp.co.topgate.sugawara.web;

import java.io.*;

/**
 * HttpResponse class
 * HttpResponseのコンテンツをバイト出力ストリームOutputStreamに書き込む
 * HttpResponse= StatusLine + MessageHeader + MessageBody
 *
 * @author sakura818
 */

public class HttpResponse {

    /**
     * HttpResponseのコンテンツをOutputStreamに書き込む
     * HttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param outputStream バイト出力ストリーム
     * @param file     ファイルパス
     * @param statusCode   ステータスコード
     * @throws IOException
     */


    public void writeToOutputStream(OutputStream outputStream, File file, int statusCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream, true);

        /** HttpResponseのStatusLineをバイト出力ストリームに書き込む */
        HttpResponseStatusLineBuilder statusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
        printWriter.println(statusLineBuilder.build());

        /** HttpResponseのMessageHeaderをバイト出力ストリームに書き込む */
        HttpResponseMessageHeaderBuilder messageHeaderContent = new HttpResponseMessageHeaderBuilder(file);
        printWriter.println(messageHeaderContent.build());

        /** HttpResponseのMessageBodyをバイト出力ストリームに書き込む */
        HttpResponseMessageBodyBuilder messageBodyBuilder = new HttpResponseMessageBodyBuilder(file,statusCode);
        outputStream.write(messageBodyBuilder.build());

        /** HttpResponseのMessageBodyの最後の印となるCRLFをバイト出力ストリームに書き込む PrintWriterクラスのprintlnメソッドと違いOutputStreamクラスのwriteメソッドでは最後改行がされないため*/
        byte[] CRLF = "\r\n".getBytes("UTF-8");
        outputStream.write(CRLF);

        /** HttpResponseMessageをコンソールに表示する */
        System.out.println("http response...");
        System.out.println(statusLineBuilder.build());
        System.out.println(messageHeaderContent.build());
        for (int i = 0; i < messageBodyBuilder.build().length; i++) {
            System.out.println(Integer.toHexString(messageBodyBuilder.build()[i]));
        }
        for (int i = 0; i < CRLF.length; i++) {
            System.out.println(Integer.toHexString(CRLF[i]));
        }
    }

}



