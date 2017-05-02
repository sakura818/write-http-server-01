package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;


/**
 * HttpRequest class
 * リクエストを分解する
 *
 * @author sakura818
 */
public class HttpRequest {

    private BufferedReader bufferedReader;
    private String requestLine;
    private String method;
    private String requestUri;
    private String httpVersion;
    private String requestHeader;
    private String emptyLine;
    private String requestMessageBody;


    HttpServer httpServer = new HttpServer();
    String requestData = httpServer.getAppendRequest();// rename

    /*
    methodの処理 getter
     */

    public String getMethod() {
        return this.method;
    }

    /*
    requestUriの処理 getter
     */

    public String getRequestUri() {
        return this.requestUri;
    }


    /*
    requestHeaderの処理 getter
     */

    public String getRequestHeader() {
        return this.requestHeader;
    }

    /*
    RequestMessageBodyの処理 getter
     */

    public void readRequest(InputStream inputStream){
    BufferedInputStream bis = new BufferedInputStream(inputStream);
    BufferedReader br = new BufferedReader(new InputStreamReader(bis));
    StringBuilder sb = new StringBuilder();
    //this.appendRequest =br.readLine();

        String line = br.readLine();


    while(!(request =br.readLine()).equals("")) {
        if (request == null) {
            System.out.println("requestはnullです");
        }
        System.out.println(request);
        sb.append(request);
        //String appendRequest = new String(sb);
    }
    //appendRequest = sb.toString();

    public String getRequestMessageBody() {
        return this.requestMessageBody;
    }

    /**
     * 改行文字を区切り文字としてrequestを4つに分割する
     * request = requestLine + requestHeader + (emptyLine) + requestMessageBody
     */

    /*
    public String[] requestDataDivide() throws IOException {
        String requestDelimiterDivide[] = requestData.split("\n");// mac,windows crlf　

        this.requestLine = requestDelimiterDivide[0];
        this.requestHeader = requestDelimiterDivide[1];
        this.emptyLine = requestDelimiterDivide[2];
        this.requestMessageBody = requestDelimiterDivide[3];

        return requestDelimiterDivide;
    }
    */

    /**
     * 空白文字を区切り文字としてrequestLineを3つに分割する
     * requestLine = method + requestUri + httpVersion
     */

    public void requestLineDivide(String statusLine)  {
        //HttpServer httpServer = new HttpServer();

        String requestUriDelimiterDivide[] = (requestLine.split(" "));

        this.method = requestUriDelimiterDivide[0];
        this.requestUri = requestUriDelimiterDivide[1];
        this.httpVersion = requestUriDelimiterDivide[2];
        //return requestUriDelimiterDivide;
    }


    /**
     * requestUriに "% HEX HEX" エンコードが使用されていたらデコードする
     */
    private String requestUriDecode(String requestUri)  {
        //requestLineDivide();
        try {
            String decodeUri = URLDecoder.decode(this.requestUri, "UTF-8");
            return decodeUri;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * requestUriからパス名を抜き出す
     */
    public String requestUriPath(String decodeUri) {
        try {
            URI requestUriPath = new URI(decodeUri);
            return requestUriPath.getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * decodeされたrequestUriからパス名を抜き出す
     */
    public String requestUriDecodeAndPath() {// rename
            String decodeUri = requestUriDecode(requestUri);
            return requestUriPath(decodeUri);

    }


}
