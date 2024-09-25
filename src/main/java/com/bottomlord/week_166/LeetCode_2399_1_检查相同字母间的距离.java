package com.bottomlord.week_166;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-09-14 08:13:52
 */
public class LeetCode_2399_1_检查相同字母间的距离 {
    public boolean checkDistances(String s, int[] distance) {
        int[] bucket = new int[26];
        Arrays.fill(bucket, -1);

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (bucket[c - 'a'] == -1) {
                bucket[c - 'a'] = i;
            } else if (distance[c - 'a'] != i - bucket[c - 'a'] - 1){
                return false;
            }
        }

        return true;
    }
}
