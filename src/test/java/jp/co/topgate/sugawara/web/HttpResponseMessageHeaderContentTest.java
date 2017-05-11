package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.junit.Assert.assertThat;
/**
 * MIMETest Class
 * ファイルの拡張子に応じてContentTypeを判断するのをテストするクラス
 * @author sakura818
 */

/**
 * Created by haruka.sugawara on 2017/05/11.
 */
public class HttpResponseMessageHeaderContentTest {

    @Test
    public void ファイルの拡張子を判断するテスト() {
        // 拡張子がNULLのファイル
        assertThat(null, is(MIME.partFileExtension(null)));

        // 拡張子がhtmlのファイル
        assertThat("html", is(MIME.partFileExtension("hoge.html")));

        // 拡張子がtxtのファイル
        assertThat("txt", is(MIME.partFileExtension("hoge.txt")));

        // 拡張子のないファイル
        assertThat(null, is(MIME.partFileExtension("hoge")));

        // ファイルのなかに連続して"."がある
        assertThat("html", is(MIME.partFileExtension("hoge..html")));

    }

    @Test
    public void ContentTypeを選択するテスト() {
        // 拡張子がNULLのファイル
        assertThat(null, is(MIME.selectContentType(null)));

        // 拡張子がhtmlのファイル
        assertThat("text/html", is(MIME.selectContentType("test.html")));

        // 拡張子がtxtのファイル
        assertThat("text/plain", is(MIME.selectContentType("test.txt")));

        // 拡張子がmapMIMEにない
        assertThat("application/octet-stream", is(MIME.selectContentType("hoge.hoge")));

        // 拡張子のないファイル
        assertThat(null, is(MIME.selectContentType("hoge")));

        // ファイルのなかに連続して"."がある
        assertThat("text/html", is(MIME.selectContentType("hoge..html")));

    }
}
