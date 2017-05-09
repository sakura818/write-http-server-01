package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/05/08.
 */
public class MIMETest {
    @Test
    public void ファイルの拡張子を判断するテスト() {
        // 拡張子がNULLのファイル
        assertThat(null, is(MIME.partFileExtension(null)));

        // 拡張子がhtmlのファイル
        assertThat("html", is(MIME.partFileExtension("hoge.html")));

        // 拡張子がtxtのファイル
        assertThat("text/plain", is(MIME.partFileExtension("hoge.txt")));

        // 拡張子のないファイル
        assertThat(null, is(MIME.partFileExtension("hoge")));

        // ファイルに二重に連続に'.'がある
        assertThat("html", is(MIME.partFileExtension("hoge..html")));

        // ファイル名の終わりに拡張子がない
        assertThat("octet-stream", is(MIME.partFileExtension("hoge.")));
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

        // ファイルに二重に連続に'.'がある
        assertThat("text/html", is(MIME.selectContentType("hoge..html")));

        // ファイル名の終わりに拡張子がない
        assertThat("application/octet-stream", is(MIME.selectContentType("hoge.")));
    }


}
