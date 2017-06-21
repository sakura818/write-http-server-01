package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseHandler extends DynamicHttpResponseHandler {

    private String nameOfFormData;
    private String textOfFromData;
    private String passwordOfFormData;
    private Map<String, String> messageBodykey;

    BoardDynamicHttpResponseHandler(File file, int statusCode, HttpRequest httpRequest, OutputStream outputStream, InputStream inputStream) throws IOException {
        System.out.println("BoardDynamicHttpResponseHandler constructor");
        Map<String,String> responseBody = analyzePostRequestBody(httpRequest);
        BoardDynamicHttpResponse boardDynamicHttpResponse = new BoardDynamicHttpResponse(file, statusCode, httpRequest, this, inputStream,responseBody);
        boardDynamicHttpResponse.writeToOutputStream(file, statusCode, httpRequest, inputStream, outputStream,responseBody);
    }

    public String dynamicHttpResponseAssort(HttpRequest httpRequest) throws IOException {
        String responseAssortFlag = "responseAssortFlagStart";
        String httpRequestMethod=  httpRequest.getMethod();
        switch (httpRequestMethod){
            case("GET"):
                if (httpRequest.getIsQueryString() == true) {
                    responseAssortFlag = "searchName";
                }
                responseAssortFlag = "topPage";
                break;
            case("POST"):
                Map<String, String> bodyValues = analyzePostRequestBody(httpRequest);
                String hiddenMethod = bodyValues.get("_method");
                
                if(hiddenMethod.equals("DELETE")){
                    responseAssortFlag = "deleteMessage";
                }
                responseAssortFlag = "postMessage";
                break;
        }
        System.out.println(responseAssortFlag);
        return responseAssortFlag;
    }


    /**
     * リクエストのボディを解析する
     *
     * @param
     * @return
     */
    Map<String, String> analyzePostRequestBody(HttpRequest httpRequest) throws IOException {
        byte[] bodyInputStream = httpRequest.getMessageBody();
        System.out.println("hana");
        String messageBodyString = new String(bodyInputStream, "UTF-8");
        String[] hoge = messageBodyString.split("&");//rename
        System.out.println(hoge.length);

        for (int count = 0; count <= hoge.length - 1; count++) {
            System.out.println(hoge[count]);
        }
        Map<String, String> messageBodyKey = new HashMap<String, String>();
        if (hoge.length != 1) {
            String[] line = hoge[0].split("=");
            messageBodyKey.put(line[0], line[1]);
            System.out.println(line[0]);
            System.out.println(line[1]);
            this.nameOfFormData = line[1];
            String[] line1 = hoge[1].split("=");
            messageBodyKey.put(line1[0], line1[1]);
            System.out.println(line1[0]);
            System.out.println(line1[1]);
            this.textOfFromData = line1[1];
            String[] line2 = hoge[2].split("=");
            messageBodyKey.put(line2[0], line2[1]);
            System.out.println(line2[0]);
            System.out.println(line2[1]);
            this.passwordOfFormData = line2[1];

            //上記の冗長な作業をfor文になおす(途中)
            for (int i = 0; i <= 1; i++) {
                String[] neko = hoge[i].split("=");
                messageBodyKey.put(line[0], line[1]);

            }
        }
        this.messageBodykey = messageBodyKey;

        return messageBodyKey;

    }

    Map<String, String> getMessageBodykey() {
        return this.messageBodykey;
    }

}
