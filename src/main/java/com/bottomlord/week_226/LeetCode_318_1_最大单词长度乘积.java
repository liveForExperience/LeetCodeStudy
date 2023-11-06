package com.bottomlord.week_226;

/**
 * @author chen yue
 * @date 2023-11-06 09:09:55
 */
public class LeetCode_318_1_最大单词长度乘积 {
    public int maxProduct(String[] words) {
        int n = words.length, index = 0;
        int[] arr = new int[n];
        for (String word : words) {
            arr[index++] = mask(word);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }

        return ans;
    }

    private int mask(String s) {
        char[] cs = s.toCharArray();
        int mask = 0;
        for (char c : cs) {
            mask |= 1 << (c - 'a');
        }
        return mask;
    }
}
