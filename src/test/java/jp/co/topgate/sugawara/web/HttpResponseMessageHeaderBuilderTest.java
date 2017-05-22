package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * HttpResponseMessageHeaderBuilderTest Class
 * ファイルの拡張子に応じてContentTypeをMapするのをテストするクラス
 *
 * @author sakura818
 */


public class HttpResponseMessageHeaderBuilderTest{
    private File filePath;
    HttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new HttpResponseMessageHeaderBuilder(filePath);

    @Test
    public void MessageHeaderをstringBuilderで文字列を連結させるテスト() {
        assertThat("Server: sakura818\nAllow: GET\nContent-Language: en\nContent-Type: text/html; charset=UTF-8\n", is(httpResponseMessageHeaderBuilder.build(new File("hoge.html"))));
    }

    @Test
    public void ファイルの拡張子からContentTypeを判断するテスト() {
        assertThat(null, is(httpResponseMessageHeaderBuilder.createContentType(null)));
        assertThat("text/html; charset=UTF-8", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.html"))));
        assertThat("text/html; charset=UTF-8", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge..html"))));
    }

    @Test
    public void ファイルから拡張子をぬきだすテスト() {
        assertThat(null, is(httpResponseMessageHeaderBuilder.extractExtension(null)));
        assertThat("html", is(httpResponseMessageHeaderBuilder.extractExtension(new File("hoge.html"))));
        assertThat("html", is(httpResponseMessageHeaderBuilder.extractExtension(new File("hoge..html"))));
    }

}

