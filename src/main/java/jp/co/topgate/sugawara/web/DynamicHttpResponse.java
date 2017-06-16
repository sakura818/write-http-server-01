package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public abstract class DynamicHttpResponse {
    //abstract byte[] createDynamicHttpResponseContent();

    public abstract byte[] createDynamicHttpResponseContent(File file, int statusCode, HttpRequest httpRequest, InputStream inputStream) throws IOException;

    //abstract void writeToOutputStream(File file, int statusCode, HttpRequest httpRequest, InputStream inputStream) throws IOException;
}
