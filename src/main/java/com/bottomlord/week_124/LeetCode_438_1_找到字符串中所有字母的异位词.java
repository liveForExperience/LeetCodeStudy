package com.bottomlord.week_124;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-11-28 18:24:31
 */
public class LeetCode_438_1_找到字符串中所有字母的异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        int len = s.length(), subLen = p.length();
        int[][] mapping = new int[len + 1][26];

        for (int i = 0; i < len; i++) {
            mapping[i + 1] = Arrays.copyOfRange(mapping[i], 0, 26);
            mapping[i + 1][s.charAt(i) - 'a']++;
        }

        int[] subBucket = new int[26];
        for (int i = 0; i < subLen; i++) {
            subBucket[p.charAt(i) - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len - subLen + 1; i++) {
            if (Arrays.equals(subBucket, get(i, i + subLen, mapping))) {
                ans.add(i);
            }
        }

        return ans;
    }

    private int[] get(int ia, int ib, int[][] mapping) {
        int[] bucket = Arrays.copyOfRange(mapping[ib], 0, mapping[ib].length);
        int[] arr = mapping[ia];
        for (int i = 0; i < arr.length; i++) {
            bucket[i] -= arr[i];
        }

        return bucket;
    }
}
