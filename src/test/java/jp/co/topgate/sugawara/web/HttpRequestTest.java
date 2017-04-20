package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

/**
 * Created by haruka.sugawara on 2017/04/19.
 */
public class HttpRequestTest {

    @Test
    public void testGetMethod() {
        // Unit TestのInputDataの準備
        List<String> requests = new ArrayList<>();//List<データ型> リストの名前 = new ArrayList<データ型>();
        requests.add("GET http://localhost:8080 HTTP/1.1");
        requests.add("Host / HTTP/1.1");

        // Unit Testしたいクラスの準備
        HttpRequest r = new HttpRequest(requests);

        // Testした結果が正しいかの確認
        assertThat(r.getMethod(), is("GET"));//assertThat("実際の値", is("期待値"));
        assertThat(r.getHttpVersion(), is("1.1"));//assertThat("実際の値", is("期待値"));
    }

    @Test
    public void testPostMethod() {
        List<String> requests = new ArrayList<>();
        requests.add("POST /?hoge=hoge HTTP/1.1");
        requests.add("Host / HTTP/1.1");

        HttpRequest r = new HttpRequest(requests);
        assertThat(r.getMethod(), is("POST"));
    }




}


