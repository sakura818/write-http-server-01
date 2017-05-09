package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * StatusCodeTest Class
 * リクエストに応じて適切なステータスコードを判断するのをテストするクラス
 * @author sakura818
 */
public class StatusCodeTest {

    @Test
    public void StatusCodeを200にしたときのテスト() {
        StatusCode status = new StatusCode();

        status.setStatusCode(200);
        assertThat(200, is(status.getStatusCode()));
        assertThat("OK", is(status.getReasonPhrase()));
        assertThat("200 OK", is(status.getStatusCodeAndReasonPhrase()));
    }

    @Test
    public void StatusCodeを400にしたときのテスト() {
        StatusCode status = new StatusCode();

        status.setStatusCode(400);
        assertThat(400, is(status.getStatusCode()));
        assertThat("Bad Request", is(status.getReasonPhrase()));
        assertThat("400 Bad Request", is(status.getStatusCodeAndReasonPhrase()));
    }

    @Test
    public void StatusCodeを404にしたときのテスト() {
        StatusCode status = new StatusCode();

        status.setStatusCode(404);
        assertThat(404, is(status.getStatusCode()));
        assertThat("Not Found", is(status.getReasonPhrase()));
        assertThat("404 Not Found", is(status.getStatusCodeAndReasonPhrase()));
    }
}