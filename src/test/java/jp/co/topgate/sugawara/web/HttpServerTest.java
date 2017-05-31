package jp.co.topgate.sugawara.web;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpServerTest Class
 *
 * @author sakura818
 */

@RunWith(Enclosed.class)
public class HttpServerTest {

    public static class connectメソッドのテスト {
        final static String FILEPATH_DIR = "src/test/resources/";

        @Test
        public void UriPathとFILEPATH_DIRからfileが生成できているかのテスト() {
            File file = new File(this.FILEPATH_DIR, "index.html");
            System.out.println(file);
            assertThat(file, is(new File("src/test/resources/index.html")));
        }
    }

    public static class catchStatusCodeメソッドのテスト {
        final static String FILEPATH_DIR = "src/test/resources/";
        HttpServer httpServer = new HttpServer();

        @Test
        public void ファイルが存在していた場合はステータスコード200を返すかテスト() {
            assertThat(httpServer.catchStatusCode(new File(this.FILEPATH_DIR, "index.html")), is(200));
        }

        @Test
        public void ファイルが存在していなかった場合はステータスコード404を返すかテスト() {
            assertThat(httpServer.catchStatusCode(new File(this.FILEPATH_DIR, "noExist.html")), is(404));
        }

    }

}




