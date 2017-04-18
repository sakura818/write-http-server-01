package jp.co.topgate.sugawara.web;

import java.io.IOException;

/**
 * Created by haruka.sugawara on 2017/04/18.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        server.connection();
    }

}
