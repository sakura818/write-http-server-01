package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyBuilderTest Class
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyBuilderTest {
    private File file;
    private int statusCode;
    HttpResponseMessageBodyBuilder httpResponseMessageBodyBuilder = new HttpResponseMessageBodyBuilder(file,statusCode);

    @Test
    public void ファイルに書き込まれている文字列がバイト型の配列に文字コードで格納できるかのテスト() throws IOException {
        file = new File("src/test/resources/sampleString.txt");
        byte[] binaryData = new byte[(int) file.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            bufferedInputStream.read(binaryData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bufferedInputStream.close();
        }
        // sampleString.txtに書き込まれている文字列"ABC"は文字コード"65,66,67"
        byte[] hoge = {65, 66, 67};
        assertArrayEquals(hoge, binaryData);
    }

}
