package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpRequestTest Class
 * リクエストを分解するのをテストするクラス
 *
 * @author sakura818
 */

public class HttpRequestTest {

    @Test
    //TODO:このメソッドはまだ書いている途中
    public void requestLineを正しく分割できているかのテスト() throws IOException {
        /** 以下の3行はhttpRequestのインスタンスを呼び出す エラー処理がクラス全体ではなくメソッドでしかできないため */
        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

    }

    @Test
    public void requestUriから正しくfileを抜き出せるかのテスト() throws IOException {
        /** 以下の3行はhttpRequestのインスタンスを呼び出す エラー処理がクラス全体ではなくメソッドでしかできないため */
        File DummyHttpRequest = new File("src/test/resources/DummyHttpRequest.txt");
        InputStream inputStream = new FileInputStream(DummyHttpRequest);
        HttpRequest httpRequest = new HttpRequest(inputStream);

        assertThat("index.html", is(httpRequest.parseFilePath("http://localhost:8080/index.html")));
    }


}


