package jp.co.topgate.sugawara.web;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpResponse {

    Socket socket = null;
    PrintWriter writer = null;


    public static String httpResponseGenerate() {//Invalid method declaration = 無効なメソッド宣言 @解決方法 public voidをpublic staticに

        String httpResponseData = null;

        String httpResponseStatusLine = null;
        String httpResponseHeaderChecked = null;
        String httpResponseMessageBodyChecked = null;

        StringBuilder builder = new StringBuilder();


        builder.append(httpResponseStatusLine).append("\n");
        builder.append(httpResponseHeaderChecked).append("\n");
        builder.append("\n");
        builder.append(httpResponseMessageBodyChecked);

        //.toString
        System.out.println("response...");

        return builder.toString();

    }

}

