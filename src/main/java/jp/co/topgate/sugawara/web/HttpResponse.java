package jp.co.topgate.sugawara.web;

import java.io.*;

/**
 * HttpResponse class
 * レスポンスのクライアントにまつわる部分をとり扱う
 * TODO:ファイルの読み込みがわかってない
 *
 * @author sakura818
 */
public class HttpResponse {

    /**
     * レスポンスを出力ストリームOutputStreamに送信する
     * @param HttpResponseContent.javaのcreateHttpResponseContentメソッドの戻り値
     * @return バイト型のデータ
     */
    public void sendHttpResponseToOutputStream(OutputStream outputStream) {
        //PrintWriter writer = new PrintWriter(outputStream, true);
        //writer.println(this.response.toString());
        /*

        HttpResponseContent.javaのcreateHttpResponseContentメソッドの戻り値を出力ストリームOutputStreamに送信したい。
        1. 戻り値をString型からバイト型に変換する .getBytes();
        2. 1で生成したバイト型のデータをOutputStreamに送信する バイト型ストリーム　バッファストリーム
        */
        HttpResponseContent httpResponseContent = new HttpResponseContent();
        byte[] byteHttpResponse = httpResponseContent.getHttpResponseContent().getBytes();
        //FileOutputStream fileOutputStream = new FileOutputStream(outputStream);
        //fileOutputStream.write(byteHttpResponse);
       // fileOutputStream.close();


    }
}



