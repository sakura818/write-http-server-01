package jp.co.topgate.sugawara.web;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * HttpResponseMessageBodyContentTest Class
 *
 * @author sakura818
 */

public class HttpResponseMessageBodyContentTest {
    private File filePath;
    private int statusCode;
    HttpResponseMessageBodyContent httpResponseMessageBodyContent = new HttpResponseMessageBodyContent();

    @Test
    public void ファイルに書き込まれている文字列がバイト型の配列に文字コードで格納できるかのテスト() throws IOException {
        filePath = new File("src/test/resources/sampleString.txt");
        byte[] binaryData = new byte[(int) filePath.length()];
        BufferedInputStream bufferedInputStream
                = new BufferedInputStream(new FileInputStream(filePath));
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
