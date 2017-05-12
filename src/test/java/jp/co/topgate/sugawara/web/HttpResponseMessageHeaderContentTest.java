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
    try{
        HttpResponseMessageHeaderContent httpResponseMessageHeaderContent = new HttpResponseMessageHeaderContent();
        File exampleRequestFile = new File("src/test/resources/exampleRequest.txt");
        InputStream inputStream = new FileInputStream(exampleRequestFile);

        @Test
        public void ファイルの拡張子からContentTypeをMapするテスト(inputStream) {
        // 拡張子がNULLのファイル
        assertThat(null, is(httpResponseMessageHeaderContent.createContentType(null)));

        // 拡張子がhtmlのファイル
        assertThat("text/html", is(HttpResponseMessageHeaderContent.createContentType("test.html")));

        // 拡張子がtxtのファイル
        assertThat("text/plain", is(HttpResponseMessageHeaderContent.createContentType("test.txt")));

        // 拡張子がmapHttpResponseMessageHeaderContentにない
        assertThat("application/octet-stream", is(HttpResponseMessageHeaderContent.createContentType("hoge.hoge")));

        // 拡張子のないファイル
        assertThat(null, is(HttpResponseMessageHeaderContent.createContentType("hoge")));

        // ファイルのなかに連続して"."がある
        assertThat("text/html", is(HttpResponseMessageHeaderContent.createContentType("hoge..html")));
        }

        @Test
        public void ファイルの拡張子を判断するテスト (file) {
        // 拡張子がNULLのファイル
        assertThat(null, is(httpResponseMessageHeaderContent.extractExtension(null)));

        // 拡張子がhtmlのファイル
        assertThat("html", is(httpResponseMessageHeaderContent.extractExtension("hoge.html")));

        // 拡張子がtxtのファイル
        assertThat("txt", is(httpResponseMessageHeaderContent.extractExtension("hoge.txt")));

        // 拡張子のないファイル
        assertThat(null, is(httpResponseMessageHeaderContent.extractExtension("hoge")));

        // ファイルのなかに連続して"."がある
        assertThat("html", is(httpResponseMessageHeaderContent.extractExtension("hoge..html")));

    }
    }catch(IOException e){
        throw new RuntimeException(e);
    }
}

