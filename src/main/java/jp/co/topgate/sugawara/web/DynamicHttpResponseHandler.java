package jp.co.topgate.sugawara.web;

import java.io.IOException;

/**
 * Created by haruka.sugawara on 2017/06/12.
 */
public abstract class DynamicHttpResponseHandler {
    //TODO: 抽象クラスを有効に使えていない
    abstract String dynamicHttpResponseAssort(HttpRequest httpRequest) throws IOException;
}
