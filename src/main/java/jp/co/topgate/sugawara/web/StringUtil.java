package jp.co.topgate.sugawara.web;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sinmetal on 4/19/17.
 */
public class StringUtil {

    public static List<String> split(String v, String separate) {
        String[] lines = v.split(separate);
        return Arrays.asList(lines);
    }
}
