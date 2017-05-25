package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyContentTest Class
 *
 * @author sakura818
 */
public class HttpResponseTest {
    //TODO:このメソッドは書いている途中

    OutputStream outputStream;
    HttpResponse httpResponse = new HttpResponse();

    @Test
    public void outputStreamに書き込めているかのテスト() throws IOException {
        //PrintWriter printWriter = new PrintWriter(outputStream, true);
        //printWriter.println("hoge");
        //System.out.println(outputStream);
        //assertThat(outputStream,is("hoge"));
        String data = "hoge";

        //outputStream.write();
        /*

        OutputStreamWriter 	ow33	= new OutputStreamWriter(System.out);
        PrintWriter 		pw33	= new PrintWriter(ow33);

        //	書き出し
        int i;
        String[] line2 = data.split("￥n");
        for(i = 0 ; i < line2.length ; i ++ )
        {
            pw33.println(line2[i]);
        }
        pw33.flush();
        /*
         */




        //byte[] CRLF = "\r\n".getBytes("UTF-8");
       // outputStream.write(CRLF);

    }

}
