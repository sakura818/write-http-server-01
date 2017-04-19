package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.io.*;
import java.util.List;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpRequest {

    private BufferedReader bufferedReader;
    private String requestLine;
    private String method;
    private String requestUri;
    private String httpVersion;
    private String requestHeader;
    private String requestMessageBody;


    String setHTTPRequest(InputStream inputStream) throws IOException {

        /*
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            this.requestLine = bufferedReader.readLine();
            System.out.println("requestLineを出力します" + requestLine);
        } catch (IOException e) {
            System.out.println("requestLineの出力に失敗しました");
        }

        return requestLine;
        */

    }



    public String getMethod() {
        method = requestLine.split("");//リクエストの最初の文字から１つめの空白の一文字手前まで
        return this.getMethod;
    }

    public String getRequestUri() {
        requestUri = requestLine.split("");//リクエストの１つめの空白の次の文字から２つめの空白の一文字手前まで
        return this.requestUri;
    }

    public String getHttpVersion()
    {
        httpVersion = requestLine.split("");//リクエストの２つめの空白の次の文字からCRLFまで
        return this.httpVersion;
    }


    public String getHeader() {
        requestHeader =  bufferedReader.split();//リクエストの１つめのCRLFから２つめのCRLFまで
        return this.requestHeader;
    }

    public String getMessageBody() {
        requestMessageBody =  bufferedReader.split();//リクエストの３つめのCRLFから文字列の末尾まで
        return this.requestMessageBody;
    }


    public HttpRequest(List<String> lines) {

    }

}
