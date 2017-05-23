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

    @Test
    public void ファイルに書き込まれている文字列をバイト型の配列に格納できているかのテスト() throws IOException {
        File sampleStringFile = new File("src/test/resources/sampleString.txt");
        byte[] binaryData = new byte[(int) sampleStringFile.length()];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sampleStringFile));
        bufferedInputStream.read(binaryData);
        bufferedInputStream.close();
        /** sampleString.txtに書き込まれている文字列"ABC"は10進数で"65,66,67 */
        byte[] hoge = {65, 66, 67};
        assertArrayEquals(hoge, binaryData);
    }

}
