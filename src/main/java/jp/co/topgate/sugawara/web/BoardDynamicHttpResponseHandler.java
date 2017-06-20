package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public class BoardDynamicHttpResponseHandler extends DynamicHttpResponseHandler {
    BoardDynamicHttpResponseHandler(File file, int statusCode, HttpRequest httpRequest, OutputStream outputStream, InputStream inputStream) throws IOException {
        System.out.println("BoardDynamicHttpResponseHandler constructor");
        BoardDynamicHttpResponse boardDynamicHttpResponse = new BoardDynamicHttpResponse(file, statusCode, httpRequest, this, inputStream);
        boardDynamicHttpResponse.writeToOutputStream(file, statusCode, httpRequest, inputStream, outputStream);
    }

    public String dynamicHttpResponseAssort(HttpRequest httpRequest) {
        String responseAssortFlag = "responseAssortFlagStart";
        if ((httpRequest.getMethod().equals("GET")) && (httpRequest.getIsQueryString() == true)) {
            responseAssortFlag = "searchName";
        } else if ((httpRequest.getMethod().equals("GET"))) {
            responseAssortFlag = "topPage";
        } else if ((httpRequest.getMethod().equals("POST"))) {
            responseAssortFlag = "postMessage";
        } else if ((httpRequest.getMethod().equals("DELETE"))) {
            responseAssortFlag = "deleteMessage";
        }
        System.out.println(responseAssortFlag);
        return responseAssortFlag;
    }
}
