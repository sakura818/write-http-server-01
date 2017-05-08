package jp.co.topgate.sugawara.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/05/08.
 */
public class StatusCodeTest {
    @Test
    public void Status管理をみるテスト() {
        StatusCode status = new StatusCode();

        status.setStatus(200);
        assertThat(200, is(status.getStatusCode()));
        assertThat("OK", is(status.getStatusMessage()));
        assertThat("200 OK", is(status.getStatus()));
    }
}
