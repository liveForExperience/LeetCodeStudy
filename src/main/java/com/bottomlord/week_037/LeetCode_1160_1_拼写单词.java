package com.bottomlord.week_037;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/17 8:15
 */
public class LeetCode_1160_1_拼写单词 {
    public int countCharacters(String[] words, String chars) {
        int[] bucket = new int[256];
        for (char c : chars.toCharArray()) {
            bucket[c]++;
        }
        int ans = 0;
        for (String word : words) {
            int[] tmp = Arrays.copyOf(bucket, bucket.length);
            boolean learn = true;
            for (char c : word.toCharArray()) {
                tmp[c]--;
                if (tmp[c] < 0) {
                    learn = false;
                    break;
                }
            }

            if (learn) {
                ans += word.length();
            }
        }

        return ans;
    }
}
