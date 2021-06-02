package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/6/2 8:48
 */
public class LeetCode_1165_2 {
    public int calculateTime(String keyboard, String word) {
        int[] bucket = new int[26];
        for (int i = 0; i < keyboard.length(); i++) {
            bucket[keyboard.charAt(i) - 'a'] = i;
        }

        int start = 0, ans = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = bucket[word.charAt(i) - 'a'];
            ans += Math.abs(index - start);
            start = index;
        }

        return ans;
    }
}
