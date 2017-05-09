package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *　Methodでレスポンスのなにを返すかわける
 * 具体的にはGETのときはFileあり　HEADのときはFileなし
 *
 *
 * @author sakura818
 */
public class Method {


    /**
     * hogehoge
     */

    public void managerMethod(int currentStatusCode, File file, OutputStream outputStream) throws IOException {
        HttpResponse httpResponse = new HttpResponse();
        StatusCode statusCode = new StatusCode();
        statusCode.setStatusCode(currentStatusCode);

    }




}