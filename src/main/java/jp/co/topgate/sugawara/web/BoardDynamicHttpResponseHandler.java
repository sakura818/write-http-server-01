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


    BoardDynamicHttpResponseHandler(File file, int statusCode, HttpRequest httpRequest, OutputStream outputStream, InputStream inputStream) throws IOException {// TODO:引数
        Map<String, String> responseBody = requestBodyParser(httpRequest);
        String queryString = httpRequest.getQueryString();
        String responseAssort = dynamicHttpResponseAssort(httpRequest) ;
        String rawPassword = getRawPassword();
        BoardDynamicHttpResponse boardDynamicHttpResponse = new BoardDynamicHttpResponse(file, statusCode, queryString, rawPassword,responseAssort, inputStream, responseBody);// TODO:引数
        boardDynamicHttpResponse.writeToOutputStream(file, statusCode, httpRequest, inputStream, outputStream, responseBody);
    }

    public String dynamicHttpResponseAssort(HttpRequest httpRequest) throws IOException {
        String httpRequestMethod = httpRequest.getMethod();
        switch (httpRequestMethod) {
            case ("GET"):
                this.responseAssort = "topPage";
                if (httpRequest.getIsQueryString()) {
                    this.responseAssort = "searchName";
                }
                break;
            case ("POST"):
                Map<String, String> responseBody = requestBodyParser(httpRequest);
                String hiddenMethod = responseBody.get("_method");
                this.responseAssort = "postMessage";
                if (!(hiddenMethod == null)) {
                    if (hiddenMethod.equals("DELETE")) {
                        this.responseAssort = "deleteMessage";
                    }
                }
                break;
        }
        return this.responseAssort;
    }


    public String getResponseAssort() {
        return responseAssort;
    }



    /**
     * リクエストのボディを解析する
     *
     * @param
     * @return
     */
    Map<String, String> requestBodyParser(HttpRequest httpRequest) throws IOException {
        byte[] bodyInputStream = httpRequest.getMessageBody();// TODO:やっぱりここまでリクエストのボディをInputStream型でもってきたほうがいいのかもしれない
        String messageBody = new String(bodyInputStream, "UTF-8");
        String[] messageBodyParse = messageBody.split("&");

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

            // TODO:上記の冗長な作業をfor文になおす(途中)


            //上記の冗長な作業をfor文になおす(途中)
            /*
            for (int i = 0; i <= 1; i++) {
                String[] linehoge = hoge[i].split("=");
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String encodePassword = bCryptPasswordEncoder.encode(rawPassword);
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
