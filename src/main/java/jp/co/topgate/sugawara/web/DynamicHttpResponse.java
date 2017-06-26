package jp.co.topgate.sugawara.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public abstract class DynamicHttpResponse {  //TODO: 抽象クラスを有効に使えていない

    public abstract byte[] createDynamicHttpResponseContent() throws IOException;
    //abstract void writeToOutputStream(File file, int statusCode, HttpRequest httpRequest, InputStream inputStream) throws IOException;

}
