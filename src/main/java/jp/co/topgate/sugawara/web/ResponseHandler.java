package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * レスポンスを生成
 *
 * @author sakura818
 */
public class ResponseHandler {

    public void handlerGET(int statusCode,File file,OutputStream outputStream){
        HttpResponse httpResponse = new HttpResponse();
        HttpServer httpServer = new HttpServer();
        httpServer.getStatusCode();
        "status" + "reason phrase";


    }



    /**
     * レスポンスの部品を集めて組み立て生成
     */

    public void generateHttpResponse(String statusLine)  {

        String httpResponseData;
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.requestLineDivide(statusLine);

        StringBuilder sb = new StringBuilder();

        sb.append(fileExistsStatusLine()).append("\n");
        sb.append(generateResponseMessageHeader()).append("\n");
        if (httpRequest.getMethod() == "GET") {
            sb.append(generateResponseMessageBody());
        }

        System.out.println("response...");
        System.out.println(sb.toString());

    }

    /**
     * エラーのページを生成
     */


    private String getErrorMessageBody(int statusCode) {
        String errorPageHtml;
        switch (statusCode) {
            case 400:
                errorPageHtml = "<html><head><title>400 Bad Request</title></head>" +
                        "<body><h1>Bad Request</h1>" +
                        "<p>リクエストにエラーがあります。<br /></p></body></html>";
                break;

            case 404:
                errorPageHtml = "<html><head><title>404 Not Found</title></head>" +
                        "<body><h1>Not Found</h1>" +
                        "<p>該当のページは見つかりませんでした。</p></body></html>";
                break;

            default:
                errorPageHtml = "<html><head><title>500 Internal Server Error</title></head>" +
                        "<body><h1>Internal Server Error</h1>" +
                        "<p>サーバー内部のエラーにより表示できません。</p></body></html>";
        }
        return errorPageHtml;
    }

    public void handleError(int statusCode, OutputStream out) {
        HttpResponse response = new HttpResponse();
        Status status = new Status();
        status.setStatus(statusCode);
        File errorfile = new File(Server, FILE_DIR, statusCode + ".html");
        if (rrorFile.exists() && errorFile.isFile() && errorFile.canRead()) {

        } else {
        }

    }
