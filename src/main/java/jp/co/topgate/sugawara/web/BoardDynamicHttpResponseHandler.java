package jp.co.topgate.sugawara.web;

import java.io.File;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseHandler extends DynamicHttpResponseHandler {
    BoardDynamicHttpResponseHandler(File file, int statusCode, HttpRequest httpRequest) {
        System.out.println("ji");
        String assort = dynamicHttpResponseAssort(httpRequest);
        BoardDynamicHttpResponse boardDynamicHttpResponse = new BoardDynamicHttpResponse(file, statusCode, httpRequest, this);
    }

    public String dynamicHttpResponseAssort(HttpRequest httpRequest) {
        String honya = "ni";
        if ((httpRequest.getMethod().equals("GET"))){
            honya = "searchName";
        } else if ((httpRequest.getMethod().equals("POST"))) {
            honya = "postMessage";
        } else if ((httpRequest.getMethod().equals("DELETE"))) {
            honya = "deleteMessage";
        }
        return honya;
    }
}
