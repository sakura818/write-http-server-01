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
    final static String FILEPATH_DIR = "src/test/resources/";
    HttpServer httpServer = new HttpServer();

    @Test
    public void UriPathとFILEPATH_DIRからfileが生成できているかのテスト() {
        File file = new File(this.FILEPATH_DIR, "index.html");
        System.out.println(file);
        assertThat(file, is(new File("src/test/resources/index.html")));
    }

    @Test
    public void ファイルが存在していた場合はステータスコード200を返すかテスト() {
        assertThat(httpServer.getStatusCode(new File(FILEPATH_DIR,"index.html")), is(200));
    }

    @Test
    public void ファイルが存在していなかった場合はステータスコード404を返すかテスト() {
        assertThat(httpServer.getStatusCode(new File(FILEPATH_DIR,"noExist.html")), is(404));
    }

}




