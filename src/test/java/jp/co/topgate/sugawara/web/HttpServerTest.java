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

    public static class catchStatusCodeメソッドのテスト {
        final String FILEPATH_DIR = "src/test/resources/";
        HttpServer httpServer = new HttpServer();

        @Test
        public void リクエストされたファイルが存在していた場合はステータスコード200を返すテスト() {
            assertThat(httpServer.catchStatusCode(new File(this.FILEPATH_DIR, "index.html")), is(200));
        }

        @Test
        public void リクエストされたファイルが存在していなかった場合はステータスコード404を返すテスト() {
            assertThat(httpServer.catchStatusCode(new File(this.FILEPATH_DIR, "noExistFile.html")), is(404));
        }

    }

}




