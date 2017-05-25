package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.ArrayList;

/**
 * HttpResponse class
 * HttpResponseのコンテンツをバイト出力ストリームOutputStreamに書き込む
 * HttpResponse = StatusLine + MessageHeader + MessageBody
 *
 * @author sakura818
 */

public class HttpResponse {


    /**
     * HttpResponseのコンテンツを並べる
     * HttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param filePath
     * @param statusCode
     * @throws Exception
     */

    public ArrayList<byte[]> createHttpResponseContents(File filePath, int statusCode) throws Exception {

        ArrayList<byte[]> createResponseContents = new ArrayList<byte[]>();
        {
            /** HttpResponseのStatusLineをバイト出力ストリームに書き込む */
            HttpResponseStatusLineBuilder statusLineBuilder = new HttpResponseStatusLineBuilder(statusCode);
            /** HttpResponseのMessageHeaderをバイト出力ストリームに書き込む */
            HttpResponseMessageHeaderBuilder messageHeaderContent = new HttpResponseMessageHeaderBuilder(filePath);
            /** HttpResponseのMessageBodyをバイト出力ストリームに書き込む */
            HttpResponseMessageBodyBuilder messageBodyBuilder = new HttpResponseMessageBodyBuilder(filePath);

            createResponseContents.add(messageHeaderContent.build());
            createResponseContents.add(statusLineBuilder.build());
            createResponseContents.add(messageBodyBuilder.build());
            byte[] CRLF = "\r\n".getBytes("UTF-8");
            createResponseContents.add(CRLF);
        }
        return  createResponseContents;
    }


    /**
     * HttpResponseのコンテンツをOutputStreamに書き込む
     * HttpResponse= StatusLine + MessageHeader + MessageBody
     *
     * @param outputStream バイト出力ストリーム
     * @throws IOException
     */

    public void writeToOutputStream(OutputStream outputStream) throws IOException {

        PrintWriter printWriter = new PrintWriter(outputStream, true);
        printWriter.println(messageHeaderContent.build());
        printWriter.println(statusLineBuilder.build());
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



