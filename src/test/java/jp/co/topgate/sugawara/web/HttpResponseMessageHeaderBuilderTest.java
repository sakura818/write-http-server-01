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


public class HttpResponseMessageHeaderBuilderTest {
    private File file;
    HttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new HttpResponseMessageHeaderBuilder(file);

    @Test
    public void EntityHeaderTestを適切な形で生成できているかのテスト() {
        assertThat("Server: sakura818\n" +
                "Allow: GET\n" +
                "Content-Language: en\n" +
                "Content-Type: text/html; charset=UTF-8\n", is(buildTest()));
    }

    public String buildTest() {
        StringBuilder messageHeader = new StringBuilder();
        messageHeader.append(createGeneralHeaderTest());
        messageHeader.append(createResponseHeaderTest());
        messageHeader.append(createEntityHeaderTest());
        return messageHeader.toString();
    }

    @Test
    public void GeneralHeaderを適切な形で生成できているかのテスト() {
        assertThat("", is(createGeneralHeaderTest()));
    }

    public String createGeneralHeaderTest() {
        StringBuilder GeneralHeader = new StringBuilder();
        return GeneralHeader.toString();
    }


    @Test
    public void messageHeaderを適切な形で生成できているかのテスト() {
        assertThat("Server: sakura818\n", is(createResponseHeaderTest()));
    }

    public String createResponseHeaderTest() {
        StringBuilder responseHeader = new StringBuilder();
        responseHeader.append("Server: " + "sakura818").append("\n");
        return responseHeader.toString();
    }

    @Test
    public void EntityHeaderを適切な形で生成できているかのテスト() {
        assertThat("Allow: GET\n" +
                "Content-Language: en\n" +
                "Content-Type: text/html; charset=UTF-8\n", is(createEntityHeaderTest()));
    }

    public String createEntityHeaderTest() {
        StringBuilder entityHeader = new StringBuilder();
        entityHeader.append("Allow: " + "GET").append("\n");
        entityHeader.append("Content-Language: " + "en").append("\n");
        entityHeader.append("Content-Type: " + "text/html; charset=UTF-8").append("\n");
        return entityHeader.toString();
    }

    @Test
    public void ファイルの拡張子に応じて適切なContentTypeをかえすテスト() {
        assertThat("text/html; charset=UTF-8", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.html"))));
        assertThat("text/html; charset=UTF-8", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.htm"))));
        assertThat("text/css", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.css"))));
        assertThat("application/javascript", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.js"))));
        assertThat("image/jpeg", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.jpg"))));
        assertThat("image/jpeg", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.jpeg"))));
        assertThat("image/png", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.png"))));
        assertThat("image/gif", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.gif"))));
        assertThat("text/plain", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.txt"))));
        assertThat("application/pdf", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.pdf"))));
        assertThat("video/mp4", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.mp4"))));
        assertThat("text/html; charset=utf-8", is(httpResponseMessageHeaderBuilder.createContentType(new File("hoge.hoge"))));
    }

    @Test
    public void ファイルから拡張子をextractするテスト() {
        assertThat("html", is(httpResponseMessageHeaderBuilder.extractExtension(new File("hoge.html"))));
        assertThat("html", is(httpResponseMessageHeaderBuilder.extractExtension(new File("hoge..html"))));
        assertThat("html", is(httpResponseMessageHeaderBuilder.extractExtension(new File("html"))));
    }

}

