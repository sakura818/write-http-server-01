package jp.co.topgate.sugawara.web;

import java.io.*;
import java.util.*;

/**
 * レスポンスのコンテンツを生成するクラス
 *
 * @author sakura818
 */

public class HttpResponseContent {
    private String httpResponseContent;

    public String getHttpResponseContent() {
        return this.httpResponseContent;
    }

    /**
     * レスポンスのコンテンツを生成
     * createHttpResponseContent = createResponseStatusLine + createResponseMessageHeader + createResponseMessageBody
     *
     * @param statusCode
     * @return httpResponseContent
     */
    public String createHttpResponseContent(int statusCode, String file) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createResponseStatusLine(statusCode)).append("\n");
        stringBuilder.append(createResponseMessageHeader(file)).append("\n");
        stringBuilder.append(createResponseMessageBody()).append("\n");
        String httpResponseContent = new String(stringBuilder);
        return httpResponseContent;
    }

    /**
     * ResponseMessageBodyを生成する
     *
     * @return ResponseMessageBody
     */

    public String createResponseMessageBody() {
        return "responseMessageBody";
    }

    private int statusCode;
    private String reasonPhrase;
    private String statusCodeAndReasonPhrase;

    /**
     * statusCodeとreasonPhraseの写像
     */

    private static final Map<Integer, String> statusCodeToReasonPhrase = new HashMap<Integer, String>() {
        {
            put(200, "OK");
            put(400, "Bad Request");
            put(404, "Not Found");
        }
    };


    // private static final String FILE_DIR = "src/main/resources/";
    // File file = new File(FILE_DIR, httpRequest.getFilePath());

    /**
     * ステータスコードに応じて適切なファイルやhtmlを返す
     * 名前がひどい　分け方よくない
     */


    public String readBinaryFile(int statusCode, File file) {
        FileInputStream fileInputStream = null;
        /*

        バイト型のファイルを読み込み、それをStringBuilderにいれるためString型に変換する。
        1. FileInputStreamでバイト型のファイルを読み込む
        2. 1で生成したバイト型のファイルをString型に変換する .toCharArray();
        */
        if (statusCode == 200) {
            try {
                fileInputStream = new FileInputStream(file);
                int i;
                while ((i = fileInputStream.read()) != -1) {
                    System.out.print((char) i);
                }
                byte b = (byte) i;
                String str = Byte.toString(b);
                return str;

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fileInputStream != null)
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
        return "200 Response Message Body";
    }

    public String badRequest(int statusCode) {
        if (statusCode == 400) {
        }
        String errorPageHtml400 = "<html><head><title>400 Bad Request</title></head>" +
                "<body><h1>Bad Request</h1>" +
                "<p>リクエストにエラーがあります。</p></body></html>";
        return errorPageHtml400;
    }

    public String notFound(int statusCode) {
        if (statusCode == 404) {
        }
        String errorPageHtml404 = "<html><head><title>404 Not Found</title></head>" +
                "<body><h1>Not Found</h1>" +
                "<p>該当のページは見つかりませんでした。</p></body></html>";
        return errorPageHtml404;
    }


}
