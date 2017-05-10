package jp.co.topgate.sugawara.web;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HttpResponse class
 * レスポンスのクライアントにまつわる部分をとり扱う
 * TODO:ファイルの読み込みがわかってない
 *
 * @author sakura818
 */
public class HttpResponse {
    private File responseMessageBodyFile;
    BufferedInputStream bis = null;
    StatusCode statusCode = new StatusCode();

    private String statusLine;
    private String extension;

    public String getStatusLine() {
        return this.statusLine;
    }
    public void setResponseMessageBodyFile(File file) {
        this.responseMessageBodyFile = file;
    }

    /**
     * レスポンスをクライアントに送信する
     */
    public void sendHttpResponse(createHttpResponseContentの戻り値) {
        PrintWriter writer = new PrintWriter(outputStream, true);



    }

    public void ResponseWriter(OutputStream outputStream, String statusLine, String responseHeader, byte[] byteContents)
            throws IOException {


        try {

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


            // PrintWriter:テキスト出力ストリームに出力する
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Stringに変えて、出力ストリームに送信
            writer.print(builder.toString());

            System.out.println("bsの中身は" + byteContents);

            dataOutputStream.write(byteContents, 0, byteContents.length);

            dataOutputStream.flush();

            dataOutputStream.close();

        } catch (IOException e) {
            System.out.println("送信エラーです");
            e.printStackTrace();
        }
    }

}

}

