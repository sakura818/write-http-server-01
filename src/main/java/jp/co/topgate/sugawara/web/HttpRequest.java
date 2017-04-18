package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.io.*;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpRequest {

    private BufferedReader bufferedReader;
    private String method;
    private String requestUri;
    private String httpVersion;
    private String requestHeader;
    private String requestMessageBody;




    public String getMethod() {
        return this.method;
    }

    public String getRequestUri() {
        return this.requestUri;
    }

    public String getHttpVersion() {
        return this.httpVersion;
    }

    public String getHeader() {
        return this.requestHeader;
    }

    public String getMessageBody() {
        return this.requestMessageBody;
    }


}
