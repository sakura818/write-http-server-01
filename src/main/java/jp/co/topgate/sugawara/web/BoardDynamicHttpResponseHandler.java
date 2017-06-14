package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseHandler extends DynamicHttpResponseHandler  {
    BoardDynamicHttpResponseHandler(File file, int statusCode, HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        System.out.println("BoardDynamicHttpResponseHandler constructor");
        BoardDynamicHttpResponse boardDynamicHttpResponse = new BoardDynamicHttpResponse(file, statusCode, httpRequest, this);
        boardDynamicHttpResponse.writeToOutputStream(outputStream);
    }

    public String dynamicHttpResponseAssort(HttpRequest httpRequest) {
        String responseAssortFlag = "responseAssortFlagStart";
        if ((httpRequest.getMethod().equals("GET"))){
            responseAssortFlag = "searchName";
        } else if ((httpRequest.getMethod().equals("POST"))) {
            responseAssortFlag = "postMessage";
        } else if ((httpRequest.getMethod().equals("DELETE"))) {
            responseAssortFlag = "deleteMessage";
        }
        return responseAssortFlag;
    }
}
