package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * HttpResponseMessageHeaderContentTest Class
 * ファイルの拡張子に応じてContentTypeをMapするのをテストするクラス
 *
 * @author sakura818
 */


public class HttpResponseMessageHeaderContentTest {

    @Test
    public void ファイルの拡張子を判断するテスト() {
        // 拡張子がNULLのファイル
        assertThat(null, is(HttpResponseMessageHeaderContent.extractExtension(null)));

        // 拡張子がhtmlのファイル
        assertThat("html", is(HttpResponseMessageHeaderContent.extractExtension("hoge.html")));

        // 拡張子がtxtのファイル
        assertThat("txt", is(HttpResponseMessageHeaderContent.extractExtension("hoge.txt")));

        // 拡張子のないファイル
        assertThat(null, is(HttpResponseMessageHeaderContent.extractExtension("hoge")));

        // ファイルのなかに連続して"."がある
        assertThat("html", is(HttpResponseMessageHeaderContent.extractExtension("hoge..html")));

    }

    @Test
    public void ファイルの拡張子からContentTypeをMapするテスト() {
        // 拡張子がNULLのファイル
        assertThat(null, is(HttpResponseMessageHeaderContent.extensionToContentType(null)));

        // 拡張子がhtmlのファイル
        assertThat("text/html", is(HttpResponseMessageHeaderContent.extensionToContentType("test.html")));

        // 拡張子がtxtのファイル
        assertThat("text/plain", is(HttpResponseMessageHeaderContent.extensionToContentType("test.txt")));

        // 拡張子がmapHttpResponseMessageHeaderContentにない
        assertThat("application/octet-stream", is(HttpResponseMessageHeaderContent.extensionToContentType("hoge.hoge")));

        // 拡張子のないファイル
        assertThat(null, is(HttpResponseMessageHeaderContent.extensionToContentType("hoge")));

        // ファイルのなかに連続して"."がある
        assertThat("text/html", is(HttpResponseMessageHeaderContent.extensionToContentType("hoge..html")));

    }
}
