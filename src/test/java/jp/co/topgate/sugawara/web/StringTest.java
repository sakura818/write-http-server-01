package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class StringTest {

    @Test
    public void TestSplit() {
        String v = "hoge fuga moge";

        String[] hoges = v.split(" ");
        assertThat(hoges.length, is(3));
        assertThat(hoges[0], is("hoge"));
        assertThat(hoges[1], is("fuga"));
        assertThat(hoges[2], is("moge"));
    }
}
