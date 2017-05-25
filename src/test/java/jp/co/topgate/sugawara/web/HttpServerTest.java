package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpServerTest Class
 *
 * @author sakura818
 */
public class HttpServerTest {
    final static String FILEPATH_DIR = "src/main/resources/";
    HttpServer httpServer = new HttpServer();

    @Test
    public void UriPathとFILEPATH_DIRからfilePathが生成できているかのテスト() {
        File filePath = new File(this.FILEPATH_DIR, "index.html");
        System.out.println(filePath);
        assertThat(filePath, is(new File("src/main/resources/index.html")));
    }

    @Test
    public void HttpRequestに応じて適切なステータスコードを返すテスト() {
        assertThat(httpServer.getStatusCode(new File(FILEPATH_DIR,"index.html")), is(200));
        assertThat(httpServer.getStatusCode(new File(FILEPATH_DIR,"noExist.html")), is(404));
    }

}




