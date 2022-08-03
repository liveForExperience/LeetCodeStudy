package com.bottomlord.week_160;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-08-03 08:47:37
 */
public class LeetCode_899_1_有序队列 {
    public String orderlyQueue(String s, int k) {
        int n = s.length();
        if (k > 1) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            return new String(cs);
        }

        String ans = s;
        for (int i = 0; i < n; i++) {
            String cur = s.substring(i, n) + s.substring(0, i);
            if (ans.compareTo(cur) > 0) {
                ans = cur;
            }
        }

        return ans;
    }
}
