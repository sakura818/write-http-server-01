package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * レスポンスを生成
 *
 * @author sakura818
 */
public class HttpHandler {

    public void handlerGET(int statusCode, File file, OutputStream outputStream) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        Status status = new Status();
        status.setStatus(statusCode);

        if (statusCode == 200) {
            if (Arrays.asList("html", "txt").contains(ContentTypeUtil.getFileExtension(file.toString()))) {
                httpResponse.addResponseHeader("Content-Type", ContentTypeUtil.getContentType(file.toString()) + "; charset=" + detectFileEncoding(file));
            } else {
                httpResponse.addResponseHeader("Content-Type", ContentTypeUtil.getContentType(file.toString()));
            }
            httpResponse.setResponseBody(file);
            httpResponse.writeTo(outputStream, status);
        } else {
            this.handlerError(statusCode, outputStream);
        }
    }


    /**
     * レスポンスの部品を集めて組み立て生成
     */

    public void generateHttpResponse(String statusLine) {

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


    private String generateResponseErrorMessageBody(int statusCode) {
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



}