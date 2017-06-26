package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseHandler extends DynamicHttpResponseHandler {

    private String nameOfFormData;
    private String textOfFromData;
    private String passwordOfFormData;
    private Map<String, String> messageBodykey;
    private String responseAssort = "responseAssortInitialize";
    private String rawPassword;


    BoardDynamicHttpResponseHandler(File file, int statusCode, HttpRequest httpRequest, OutputStream outputStream, InputStream inputStream) throws IOException {
        Map<String, String> responseBody = analyzePostRequestBody(httpRequest);
        BoardDynamicHttpResponse boardDynamicHttpResponse = new BoardDynamicHttpResponse(file, statusCode, httpRequest, this, inputStream, responseBody);
        boardDynamicHttpResponse.writeToOutputStream(file, statusCode, httpRequest, inputStream, outputStream, responseBody);
    }

    public String dynamicHttpResponseAssort(HttpRequest httpRequest) throws IOException {
        String httpRequestMethod = httpRequest.getMethod();
        switch (httpRequestMethod) {
            case ("GET"):
                responseAssort = "topPage";
                if (httpRequest.getIsQueryString()) {
                    responseAssort = "searchName";
                }
                break;
            case ("POST"):
                Map<String, String> responseBody = analyzePostRequestBody(httpRequest);
                String hiddenMethod = responseBody.get("_method");
                responseAssort = "postMessage";
                if (!(hiddenMethod == null)) {
                    if (hiddenMethod.equals("DELETE")) {
                        responseAssort = "deleteMessage";
                    }
                }
                break;
        }
        return responseAssort;
    }


    /**
     * リクエストのボディを解析する
     *
     * @param
     * @return
     */
    Map<String, String> analyzePostRequestBody(HttpRequest httpRequest) throws IOException {
        byte[] bodyInputStream = httpRequest.getMessageBody();
        String messageBody = new String(bodyInputStream, "UTF-8");
        String[] messageBodyParse = messageBody.split("&");//rename

        for (int count = 0; count <= messageBodyParse.length - 1; count++) {
        }
        Map<String, String> messageBodyKey = new HashMap<String, String>();
        if (messageBodyParse.length != 1) {
            String[] line = messageBodyParse[0].split("=");
            messageBodyKey.put(line[0], URLDecoder.decode((line[1]), "UTF-8"));
            this.nameOfFormData = URLDecoder.decode(line[1], "UTF-8");
            String[] line1 = messageBodyParse[1].split("=");
            messageBodyKey.put(line1[0], URLDecoder.decode((line1[1]), "UTF-8"));
            this.textOfFromData = URLDecoder.decode(line1[1], "UTF-8");
            String[] line2 = messageBodyParse[2].split("=");
            this.rawPassword = URLDecoder.decode(line2[1], "UTF-8");

            System.out.println(this.passwordOfFormData);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodePassword = bCryptPasswordEncoder.encode(rawPassword);
            this.passwordOfFormData = encodePassword;
            messageBodyKey.put(line2[0], this.passwordOfFormData);


            //上記の冗長な作業をfor文になおす(途中)
            /*
            for (int i = 0; i <= 1; i++) {
                String[] neko = hoge[i].split("=");
                messageBodyKey.put(line[0], line[1]);

            }
            */
        }
        this.messageBodykey = messageBodyKey;

        return messageBodyKey;
    }


    Map<String, String> getMessageBodykey() {
        return this.messageBodykey;
    }

    public String getRawPassword() {
        return rawPassword;
    }

}
