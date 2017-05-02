package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * レスポンスを生成
 *
 * @author sakura818
 */
public class ResponseHandler {


    /**
     * レスポンスの部品を集めて組み立て生成
     */

    public void generateHttpResponse(String statusLine) throws IOException {

        String httpResponseData;
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.requestLineDivide(statusLine);

        StringBuilder sb = new StringBuilder();

        sb.append(fileExistsStatusLine()).append("\n");
        sb.append(generateResponseMessageHeader()).append("\n");
        if(httpRequest.getMethod() == "GET"){
            sb.append(generateResponseMessageBody());
        }

        System.out.println("response...");
        System.out.println(sb.toString());

    }
}
