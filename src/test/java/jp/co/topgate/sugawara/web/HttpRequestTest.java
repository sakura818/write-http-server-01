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
    public void メソッドHttpRequestがリクエストからmethodとrequestUriとhttpversionを抜き出す() {

    }


    @Test
    public void メソッドGetRequestFileがリクエストURIとファイルパスから呼び出すファイルを特定する() {
        // Unit TestのInputDataの準備
        List<String> requests = new ArrayList<>();//List<データ型> リストの名前 = new ArrayList<データ型>();
        requests.add("http://localhost:8080/hello.html");
        requests.add("http://localhost:8080/cream.png");
        requests.add("http://localhost:8080/cream.gif");
        requests.add("http://localhost:8080/cream.jpeg");
        requests.add("http://localhost:8080/date.js");
        requests.add("http://localhost:8080/.css");
        requests.add("http://localhost:8080/honyarara.html");
        requests.add("http://localhost:8080/");

        // Unit Testしたいクラスの準備
        HttpRequest requestData = new HttpRequest();

        // Testした結果が正しいかの確認
        assertThat(requestData.getRequestFile(), is("hello.html"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("cream.png"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("cream.gif"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("cream.jpeg"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("date.js"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("red.css"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is(null));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is(null));//assertThat("実際の値", is("期待値"));

    }

    @Test
    public void メソッドConvertRequestUriToExtensionがURLからファイルの拡張子を判別する() {
        // Unit TestのInputDataの準備
        List<String> requests = new ArrayList<>();//List<データ型> リストの名前 = new ArrayList<データ型>();
        requests.add("http://localhost:8080/hello.html");
        requests.add("http://localhost:8080/cream.png");
        requests.add("http://localhost:8080/cream.gif");
        requests.add("http://localhost:8080/cream.jpeg");
        requests.add("http://localhost:8080/date.js");
        requests.add("http://localhost:8080/.css");
        requests.add("http://localhost:8080/honyarara.html");
        requests.add("http://localhost:8080/");

        // Unit Testしたいクラスの準備
        HttpRequest requestData = new HttpRequest();

        // Testした結果が正しいかの確認
        assertThat(requestData.getRequestFile(), is("html"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("png"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("gif"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("jpeg"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("js"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is("css"));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is(null));//assertThat("実際の値", is("期待値"));
        assertThat(requestData.getRequestFile(), is(null));//assertThat("実際の値", is("期待値"));

    }

}


