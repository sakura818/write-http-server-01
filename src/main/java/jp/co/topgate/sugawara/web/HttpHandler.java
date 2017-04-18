package jp.co.topgate.sugawara.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by haruka.sugawara on 2017/04/17.
 */
public class HttpHandler {

    public HttpHandler(InputStream inputStream, OutputStream outputStream, Integer PORT) throws IOException {

        public static final String FILE_PATH = “./src/main/java/Document”;

        File requestResource = httpRequest.getRequestResource();


        System.out.println("リクエストリソースは" + requestResource);

        if (requestResource.exists()) {
            System.out.println("ファイルを見つけました。レスポンスを生成します");
            httpResponse.makeResponseBody(requestResource);
            httpResponse.sendResponse(HTTPResponse.MESSAGE_OK, httpRequest.getRequestResourceExtension(requestResource));
        } else {
            ErrorPage errorPage = new ErrorPage();
            System.out.println("ファイルが見つかりませんでした");
            errorPage.setErrorMessage("404 NOT Found");
            httpResponse.makeResponseBody(requestResource);
            httpResponse.sendResponse(HTTPResponse.MESSAGE_NOT_FOUND, httpRequest.getRequestResourceExtension(requestResource));
        }
    }

    static void handleError(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {
        System.out.print("エラーページを表示します");
        File requestResource = httpRequest.getRequestResource();
        httpResponse.sendResponse(HTTPResponse.MESSAGE_INTERNAL_SERVER_ERROR, httpRequest.getRequestResourceExtension(requestResource));
    }


    }
}
