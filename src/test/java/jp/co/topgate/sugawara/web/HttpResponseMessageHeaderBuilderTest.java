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
    private File filePath = new File("src/test/resources/index.html");
    HttpResponseMessageHeaderBuilder httpResponseMessageHeaderBuilder = new HttpResponseMessageHeaderBuilder(filePath);

    @Test
    public void EntityHeaderTestを適切な形で生成できているかのテスト() {
        assertThat((buildTest()), is("Server: sakura818\n" +
                "Allow: GET\n" +
                "Content-Language: en\n" +
                "Content-Type: text/html; charset=UTF-8\n"));
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
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.html"))), is("text/html; charset=UTF-8"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.htm"))), is("text/html; charset=UTF-8"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.css"))), is("text/css"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.js"))), is("application/javascript"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.jpg"))), is("image/jpeg"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.jpeg"))), is("image/jpeg"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.png"))), is("image/png"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.gif"))), is("image/gif"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.txt"))), is("text/plain"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.pdf"))), is("application/pdf"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.mp4"))), is("video/mp4"));
        assertThat((httpResponseMessageHeaderBuilder.createContentType(new File("hoge.hoge"))), is("text/html; charset=utf-8"));
    }

    @Test
    public void ファイルから拡張子をextractするテスト() {
        assertThat((httpResponseMessageHeaderBuilder.extractExtension(new File("hoge.html"))), is("html"));
        assertThat((httpResponseMessageHeaderBuilder.extractExtension(new File("hoge..html"))), is("html"));
        assertThat((httpResponseMessageHeaderBuilder.extractExtension(new File("html"))), is("html"));
    }

}

