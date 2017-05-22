package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * HttpResponseMessageHeaderContentTest Class
 * ファイルの拡張子に応じてContentTypeをMapするのをテストするクラス
 *
 * @author sakura818
 */


public class HttpResponseMessageHeaderContentTest {
    private File filePath;
    HttpResponseMessageHeaderContent httpResponseMessageHeaderContent = new HttpResponseMessageHeaderContent(filePath);

    @Test
    public void MessageHeaderをstringBuilderで文字列を連結させるテスト() {
        assertThat("Server: sakura818\nAllow: GET\nContent-Language: en\nContent-Type: text/html; charset=UTF-8\n", is(httpResponseMessageHeaderContent.createHttpResponseMessageHeader(new File("hoge.html"))));
    }

    @Test
    public void ファイルの拡張子からContentTypeを判断するテスト() {
        assertThat(null, is(httpResponseMessageHeaderContent.createContentType(null)));
        assertThat("text/html; charset=UTF-8", is(httpResponseMessageHeaderContent.createContentType(new File("hoge.html"))));
        assertThat("text/html; charset=UTF-8", is(httpResponseMessageHeaderContent.createContentType(new File("hoge..html"))));
    }

    @Test
    public void ファイルから拡張子をぬきだすテスト() {
        assertThat(null, is(httpResponseMessageHeaderContent.extractExtension(null)));
        assertThat("html", is(httpResponseMessageHeaderContent.extractExtension(new File("hoge.html"))));
        assertThat("html", is(httpResponseMessageHeaderContent.extractExtension(new File("hoge..html"))));
    }

}

