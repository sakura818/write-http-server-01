package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.io.*;

/**
 * Created by haruka.sugawara on 2017/04/13.
 */
public class HttpRequest {
    BufferedReader br = null;
    public static void httpRequestDataParse(InputStream br) throws IOException{
        //httpRequestDataを分割していく

        String[] httpRequestParser = new String[3];

        HttpServer httpServer = new HttpServer();

        httpServer.br = null;//cannnot resolve symbol br @解決方法　BufferedReader br = null;をpublicにする
        String httpRequestLine = null;
        String httpRequestCRLF = null;
        String httpRequestHeader = null;
        String httpRequestMessageBody =null;

        String splittString = br.toString();//むずい
        httpRequestParser = splittString.split("¥n");//むずい @解決方法　String[]の宣言を消去


        httpRequestLine = httpRequestParser[0];
        httpRequestHeader = httpRequestParser[1];
        httpRequestCRLF = httpRequestParser[2];
        httpRequestMessageBody = httpRequestParser[3];

    }
}

