package jp.co.topgate.sugawara.web;

import java.util.HashMap;

/**
 * Created by haruka.sugawara on 2017/04/17.
 */
public class StatusCode {
    private static Integer statusCode;
    private static String reasonPhrase;

    private static String StatusParameter(int status) {
        final HashMap<Integer, String> statusCodeReasonReasonPhrase = new HashMap<Integer, String>() {
            {
                put(100, "Continue");

                put(200, "OK");

                put(300, "Multiple Choices");

                put(400, "Bad Request");
                put(403, "Forbidden");
                put(404, "Not Found");

                put(500, "Internal Server Error");
                put(503, "Service Unavailable");
                put(505, "HTTP Version Not Supported");
            }
        };

        if (statusCodeReasonReasonPhrase.containsKey(status)) {
            return status + " " + statusCodeReasonReasonPhrase.get(status);
        } else {
            return "Unknown Error";
        }
    }

    public static void setStatusCode(int i) {
        statusCode = i;
    }

    public static void setStatus(int i){
        reasonPhrase = StatusParameter(i);
    }

    public static String getStatus() {
        setStatus(statusCode);
        return reasonPhrase;
    }

}
