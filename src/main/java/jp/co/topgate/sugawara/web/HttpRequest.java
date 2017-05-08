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



    public void readRequest(InputStream inputStream) {
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            String requestLine = br.readLine();

            this.requestLineDivide(requestLine);
            StringBuilder sb = new StringBuilder();

            while (!(requestLine).equals("")) {
                sb.append(requestLine).append("¥n");
            }
            this.requestHeader = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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




    /**
     * 空白文字を区切り文字としてrequestLineを3つに分割する
     * requestLine = method + requestUri + httpVersion
     * /


    public void requestLineDivide(String statusLine) {
        //HttpServer httpServer = new HttpServer();

        String requestUriDelimiterDivide[] = (requestLine.split(" "));

        this.method = requestUriDelimiterDivide[0];
        this.requestUri = requestUriDelimiterDivide[1];
        this.httpVersion = requestUriDelimiterDivide[2];
        //return requestUriDelimiterDivide;
    }


    /**
     * HTTPジェネラルヘッダを読み込み、整理します.
     *
     * @param line
     * @param host
     */
    private void readRequestLine(String line, String host) throws IOException {
        HTTPRequestLine header = new HTTPRequestLine(line);
        this.method = header.getMethod();
        this.filePath = uriQuerySplitter(urlDivider(header.getFilePath(), host));
        if (this.filePath.endsWith("/")) {
            this.filePath += "index.html";
        }
        this.protocolVer = ProtocolVer(header.getProtocol());
    }


    /**
     * 空白文字を区切り文字としてrequestLineを3つに分割する
     * requestLine = method + requestUri + httpVersion
     *
     * @param line
     * @param host
     */
    public void requestLineDivide(String requestLine) {
        RequestLine requestLine = new RequestLine(line);

        String requestUriDelimiterDivide[] = (requestLine.split(" "));

        this.method = requestUriDelimiterDivide[0];
        this.requestUri = requestUriDelimiterDivide[1];
        this.httpVersion = requestUriDelimiterDivide[2];
        //return requestUriDelimiterDivide;
    }


    /**
     * requestUriからパス名を抜き出す
     */
    public String requestUriPath(String requestUri) {
        try {
            URI requestUriPath = new URI(requestUri);
            return requestUriPath.getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);

        }
    }


}
