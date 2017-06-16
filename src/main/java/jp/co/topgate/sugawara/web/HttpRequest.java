package jp.co.topgate.sugawara.web;


import java.io.IOException;
import java.lang.String;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;



/**
 * HttpRequest class
 * inputStreamからHttpRequestを読み込む処理を行う
 * HttpRequest = RequestLine + MessageHeader + MessageBody
 * 今回の課題では簡易的な機能しか提供しないためMessageHeaderとMessageBodyは読み込んでいない
 *
 * @author sakura818
 */

public class HttpRequest {
    private String uriPath;
    private String requestUri;
    private int statusCode;
    private String queryString;

    private boolean isQueryString;
    private final int OK = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_IMPLEMENTED = 501;
    private final int HTTP_VERSION_NOT_SUPPORTED = 505;

    private String method;


    /**
     * HttpRequestのコンストラクタ
     *
     * @param inputStream
     */

    public HttpRequest(InputStream inputStream) throws IOException {
        String requestLine = readRequestLine(inputStream);
        Map<String, String> header = HttpRequestParse(inputStream);


        //Map<String,String> readMessageHeader(inputStream);
        //InputStream readMessageBody(inputStream);
        int statusCode = judgeStatusCode(requestLine);

        this.statusCode = statusCode;

        if (statusCode == 200) {
            String method = parseMethod(requestLine);
            this.method = method;


            String requestUri = parseRequestUri(requestLine);
            this.requestUri = requestUri;

            String uriPath = parseUriPath(requestUri);
            String[] uriPathAndQueryString = uriPath.split("\\?", 2);
            this.uriPath = uriPathAndQueryString[0];
            if (uriPathAndQueryString.length == 2) {
                this.queryString = uriPathAndQueryString[1];
                this.isQueryString = true;
            } else if (uriPathAndQueryString.length == 1) {
                this.queryString = "";
                this.isQueryString = false;

            }
        }
    }

    /**
     * requestLineからstatusCodeをjudgeする
     * requestLine = method + requestUri + httpVersion
     *
     * @param requestLine ex:GET /index.html HTTP/1.1
     * @return statusCode ex:200
     */

    int judgeStatusCode(String requestLine) {
        if (requestLine != null) {
            String[] requestLineArray = requestLine.split(" ", 3);
            if ((requestLineArray.length == 3) && (implementedHttpMethod.contains(requestLineArray[0]) == true) && (supportedHttpVersions.contains(requestLineArray[2]) == true)) {
                statusCode = OK;
            } else if ((requestLineArray.length == 3) && (notImplementedHttpMethod.contains(requestLineArray[0]) == true)) {
                statusCode = NOT_IMPLEMENTED;
            } else if ((requestLineArray.length == 3) && (notSupportedHttpVersions.contains(requestLineArray[2]) == true)) {
                statusCode = HTTP_VERSION_NOT_SUPPORTED;
            } else {
                statusCode = BAD_REQUEST;
            }
        }
        return statusCode;
    }



    /**
     * requestLineからUriPathをparseする
     * requestLine = method + requestUri + httpVersion
     *
     * @param requestLine ex:GET /index.html HTTP/1.1
     * @return requestUri ex:index.html
     */

    String parseRequestUri(String requestLine) throws UnsupportedEncodingException {
        if (requestLine != null) {
            String[] requestLineArray = requestLine.split(" ", 3);
            String decodedRequestUri = URLDecoder.decode(requestLineArray[1], "UTF-8");
            requestUri = decodedRequestUri;
            if (requestUri.equals("/")) {
                requestUri += "index.html";
            }
        }
        return requestUri;
    }


    /**
     * このHttpServerで実装しておりRFC2616に記載されているmethodのリスト
     */

    List<String> implementedHttpMethod = new ArrayList<String>() {
        {
            add("GET");
            add("POST");
            add("DELETE");
        }
    };

    /**
     * このHttpServerで実装していないがRFC2616に記載されているmethodのリスト
     */

    List<String> notImplementedHttpMethod = new ArrayList<String>() {
        {
            add("HEAD");
            add("PUT");
            add("TRACE");
            add("HEAD");
            add("CONNECT");
        }
    };

    /**
     * このHttpServerでサポートしておりRFC2616に記載されているhttpVersionのリスト
     */

    List<String> supportedHttpVersions = new ArrayList<String>() {
        {
            add("HTTP/1.1");
        }
    };

    /**
     * このHttpServerでサポートしていないがRFC2616に記載されているhttpVersionのリスト
     */

    List<String> notSupportedHttpVersions = new ArrayList<String>() {
        {
            add("HTTP/0.9");
            add("HTTP/1.0");
            add("HTTP/2");
        }
    };

    /**
     * requestLineからrequestUriをparseする
     * requestLine = method + requestUri + httpVersion
     *
     * @param requestLine ex:GET /index.html HTTP/1.1
     * @return requestUri ex:index.html
     */



    /**
     * requestUriからUriPathを抜き出す
     *
     * @param requestUri ex:http://localhost:8080/index.html
     * @return UriPath ex:/index.html
     */

    String parseUriPath(String requestUri) {
        String host = "http://localhost:8080";
        if (requestUri != null) {
            if (requestUri.startsWith(host)) {
                requestUri = requestUri.replace(host, "");
            }
            uriPath = requestUri;
        }
        return uriPath;
    }

    /**
     * uriPathにクエリ文字列があったときuriPathとクエリ文字列を分ける
     *
     * @param uriPath /index.html?id=1&name=hana
     * @return queryString
     */
    /*
    public String[] divideUriPathAndQueryString(String uriPath) throws UnsupportedEncodingException {
        if (uriPath != null) {
        String[] uriPathAndQueryString = uriPath.split("\\?",2);
            if(uriPathAndQueryString.length == 2){
                uriPath = uriPathAndQueryString[0];
                queryString = uriPathAndQueryString[1];
            }
            if(uriPathAndQueryString.length == 1){
                uriPath = uriPathAndQueryString[0];
                queryString = "";
            }
        }
        return uriPathAndQueryString;
    }
    */


    /**
     * statusCodeを取得する
     *
     * @return statusCode
     */

    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * statusCodeを取得する
     *
     * @return statusCOde
     */

    public String getRequestUri() {
        return this.requestUri;
    }


    /**
     * uriPathを取得する
     *
     * @return uriPath
     */

    public String getUriPath() {
        return this.uriPath;
    }

    /**

     * inputStreamからrequestLineを読み取る
     *
     * @return uriPath
     */

    public String readRequestLine(InputStream inputStream) throws IOException {
        String requestLine = readLine(inputStream);
        System.out.println(requestLine);


        return requestLine;
    }

    /**
     * inputStreamからmessageheaderを読み取る
     *
     * @return uriPath
     */

    public void readMessageHeader(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        String requestLine = bufferedReader.readLine();
        //System.out.println(requestLine);
    }


    /**
     * requestLineからmethodをparseする
     *
     * @param requestLine
     * @return method
     */

    public String parseMethod(String requestLine) {
        String[] requestLineArray = requestLine.split(" ", 3);
        String method = requestLineArray[0];
        return method;
    }

    /**
     * methodを取得する
     *
     * @return method
     */

    public String getMethod() {
        return this.method;
    }

    public Map<String, String> HttpRequestParse(InputStream inputStream) throws IOException {
        Map<String, String> headerField = new HashMap<String, String>();

        String line = readLine(inputStream);
        StringBuffer header = new StringBuffer();
        while (line != null && !line.isEmpty()) {
            header.append(line).append("\n");
            String[] headerLineData = line.split(":", 2);
            if (headerLineData.length == 2) {
                headerField.put(headerLineData[0], headerLineData[1].trim());
            }
            line = readLine(inputStream);
            System.out.println(line);
        }
        setHeader(header.toString(), headerField);
        return headerField;
    }


    private String header;
    private Map<String, String> headerField = new HashMap<>();

    void setHeader(String header, Map<String, String> headerField) {
        this.header = header;
        this.headerField = headerField;
    }

    private InputStream bodyInput;

    void setBody(InputStream inputStream) {
        this.bodyInput = inputStream;
    }


    public String readLine(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        int num = 0;
        StringBuffer stringBuffer = new StringBuffer();
        boolean r = false;
        try {
            while ((num = inputStream.read()) >= 0) {
                stringBuffer.append((char) num);
                String line = stringBuffer.toString();
                switch ((char) num) {
                    case '\r':
                        r = true;
                        break;
                    case '\n':
                        if (r) {
                            line = line.replace("\r", "");
                        }
                        line = line.replace("\n", "");
                        return line;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (stringBuffer.length() == 0) {
            return null;
        } else {
            return stringBuffer.toString();
        }
    }

    /**
     * クエリ文字列を取得する
     *
     * @return queryString
     */

    public String getQueryString() {
        return this.queryString;
    }

    /**
     * クエリ文字列の有無取得する
     *
     * @return
     */

    public boolean getIsQueryString() {
        return this.isQueryString;

    }
}







