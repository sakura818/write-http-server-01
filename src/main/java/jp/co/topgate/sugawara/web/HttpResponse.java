package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpResponse {

    Socket socket = null;
    PrintWriter writer = null;


    public static String httpResponseGenerate() {//Invalid method declaration = 無効なメソッド宣言 @解決方法 public voidをpublic staticに

        String httpResponseData = null;

        String statusLine = null;
        String responseHeader = null;
        String responseMessageBody = null;

        StringBuilder builder = new StringBuilder();
        //画像はbytestream

        builder.append(statusLine).append("\n");
        builder.append(responseHeader).append("\n");
        builder.append("\n");
        builder.append(responseMessageBody);


        System.out.println("response...");
        return builder.toString();

    }


    public boolean FileExistsCheck(File file) {
        if (file.exists()) {
            System.out.println("ファイルは存在しています");
            return true;
        } else {
            System.out.println("ファイルは存在していません");
            return false;
        }
    }

    public String createStatusLine(boolean FileExistsCheck) {

        String statusLine;

        if (FileExistsCheck == true) {
            statusLine = "HTTP/1.1 200 OK";
        } else {
            statusLine = "HTTP/1.1 404 Not Found";
        }
        return statusLine;
    }


    public String statusLine(String statuscode) {

        HashMap<String, String> statusLineMap = new HashMap<String, String>();

        statusLineMap.put("200", "HTTP/1.1 200 OK");
        //statusLineMap.put("400", "HTTP/1.1 400 Bad Request");
        statusLineMap.put("404", "HTTP/1.1 404 Not Found");

        return "";

    }


    public String contentType(String Extension) {
        String contentType = null;

        HashMap<String, String> contentTypeExtension = new HashMap<String, String>();

        contentTypeExtension.put("html", "Content-Type: text/html");
        contentTypeExtension.put("css", "Content-Type: text/css");
        contentTypeExtension.put("js", "Content-Type: text/js");
        contentTypeExtension.put("png", "Content-Type: image/png");
        contentTypeExtension.put("jpg", "Content-Type: image/jpeg");
        contentTypeExtension.put("jpeg", "Content-Type: image/jpeg");
        contentTypeExtension.put("gif", "Content-Type: image/gif");

        return "";

    }


    public String responseHeader(String contentType) {

        return "";


    }


    public String responseMessageBody(String messageBody) {

        return "";


    }


}

