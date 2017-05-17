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
    public void ファイルの拡張子に応じて適切なContentTypeを判断するテスト() {
        assertThat(null, is(httpResponseMessageHeaderContent.createContentType(null)));

        assertThat("text/html", is(httpResponseMessageHeaderContent.createContentType(new File("test.html"))));

        assertThat("application/octet-stream", is(httpResponseMessageHeaderContent.createContentType(new File("hoge.hoge"))));

        assertThat(null, is(httpResponseMessageHeaderContent.createContentType(new File("hoge"))));

        assertThat("text/html", is(httpResponseMessageHeaderContent.createContentType(new File("hoge..html"))));
    }

    @Test
    public void ファイルから拡張子をぬきだすテスト() {
        assertThat(null, is(httpResponseMessageHeaderContent.extractExtension(null)));

        assertThat("html", is(httpResponseMessageHeaderContent.extractExtension(new File("hoge.html"))));

        assertThat(null, is(httpResponseMessageHeaderContent.extractExtension(new File("hoge"))));

        assertThat("html", is(httpResponseMessageHeaderContent.extractExtension(new File("hoge..html"))));

    }


}

