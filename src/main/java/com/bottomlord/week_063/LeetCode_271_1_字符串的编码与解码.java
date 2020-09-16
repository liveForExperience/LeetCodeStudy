package com.bottomlord.week_063;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/14 19:01
 */
public class LeetCode_271_1_字符串的编码与解码 {
    public String encode(List<String> strs) {
        if(strs.size() == 0) {
            return null;
        }

        String sep = Character.toString((char) 257);
        return String.join(sep, strs);
    }

    public List<String> decode(String s) {
        if (s == null) {
            return new ArrayList<>();
        }

        String sep = Character.toString((char) 257);
        return Arrays.asList(s.split(sep, -1));
    }
}
