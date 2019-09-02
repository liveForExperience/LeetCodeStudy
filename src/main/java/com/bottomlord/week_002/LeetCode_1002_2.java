package com.bottomlord.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/21 16:44
 */
public class LeetCode_1002_2 {
    public List<String> commonChars(String[] A) {
        int[] dict = new int[26];
        for (char c: A[0].toCharArray()) {
            dict[c - 'a']++;
        }

        for (String str: A) {
            int[] tmp = new int[26];
            for (char c: str.toCharArray()) {
                tmp[c - 'a']++;
            }

            for (int i = 0; i < dict.length; i++) {
                if (dict[i] > 0) {
                    dict[i] = Math.min(dict[i], tmp[i]);
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < dict.length; i++) {
            while (dict[i]-- > 0) {
                ans.add(String.valueOf((char)(i + 'a')));
            }
        }
        return ans;
    }
}
