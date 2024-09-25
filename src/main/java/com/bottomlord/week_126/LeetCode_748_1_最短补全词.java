package com.bottomlord.week_126;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2021-12-10 08:47:50
 */
public class LeetCode_748_1_最短补全词 {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] bucket = new int[26];
        char[] cs = licensePlate.toCharArray();
        for (char c : cs) {
            if (!Character.isAlphabetic(c)) {
                continue;
            }

            if (Character.getType(c) == Character.UPPERCASE_LETTER) {
                bucket[c - 'A']++;
            } else {
                bucket[c - 'a']++;
            }
        }

        int min = Integer.MAX_VALUE;
        String ans = null;
        for (String word : words) {
            int[] arr = getCountArr(word);
            boolean flag = true;
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] > arr[i]) {
                    flag = false;
                    break;
                }
            }

            if (flag && word.length() < min) {
                ans = word;
                min = word.length();
            }
        }

        return ans;
    }

    private int[] getCountArr(String word) {
        int[] arr = new int[26];
        char[] cs = word.toCharArray();
        for (char c : cs) {
            arr[c - 'a']++;
        }
        return arr;
    }
}
