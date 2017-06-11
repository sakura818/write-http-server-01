package jp.co.topgate.sugawara.web;

import java.io.OutputStream;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public abstract class DynamicHttpResponse {
    abstract byte[] createDynamicHttpResponseContent();
    abstract void writeToOutputStream(OutputStream outputStream);
}
