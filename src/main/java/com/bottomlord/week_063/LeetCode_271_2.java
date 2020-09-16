package com.bottomlord.week_063;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/15 8:48
 */
public class LeetCode_271_2 {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(int2String(str.length()));
            sb.append(str);
        }
        return sb.toString();
    }

    private String int2String(int num) {
        char[] cs = new char[4];
        for (int i = 3; i >=0; i--) {
            cs[3 - i] = (char) (num >> (8 * i) & 0xff);
        }
        return new String(cs);
    }

    public List<String> decode(String s) {
        int index = 0, n = s.length();
        List<String> ans = new ArrayList<>();
        while (index < n) {
            int len = string2Int(s.substring(index, index + 4));
            index += 4;
            ans.add(s.substring(index, index + len));
            index += len;
        }
        return ans;
    }

    private int string2Int(String s) {
        int num = 0;
        for (char c : s.toCharArray()) {
            num = num << 8 + c;
        }
        return num;
    }
}
