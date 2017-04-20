package jp.co.topgate.sugawara.web;

import java.io.File;
import java.util.HashMap;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpResponse {

    public static String httpResponseGenerate() {

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

    public String createContentType(String Extension) {

        String contentType = null;

        if ((Extension.equals("html")) || (Extension.equals("css")) || Extension.equals("js")) {
            contentType = "Content-Type: text/" + Extension;
        } else if ((Extension.equals("png")) || Extension.equals("jpeg") || Extension.equals("gif")) {
            contentType = "Content-Type: image/" + Extension;
        } else {
            contentType = "Content-Type: text/plain";
        }
        return contentType;
    }


    public String responseHeader(String contentType) {

        return "";

    }


    public String responseMessageBody(String messageBody) {

        return "";

    }


}

