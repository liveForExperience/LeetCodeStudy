package com.bottomlord.week_057;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/6 12:26
 */
public class LeetCode_336_2 {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(new StringBuilder(words[i]).reverse().toString(), i);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            if (len == 0) {
                continue;
            }

            for (int j = 0; j <= len; j++) {
                if (isPalindrome(words[i], j, len - 1)) {
                    int index = map.getOrDefault(words[i].substring(0, j), -1);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(i, index));
                    }
                }

                if (j != 0 && isPalindrome(words[i], 0, j - 1)) {
                    int index = map.getOrDefault(words[i].substring(j, len), -1);
                    if (index != -1 && index != i) {
                        ans.add(Arrays.asList(index, i));
                    }
                }
            }
        }

        return ans;
    }

    private boolean isPalindrome(String word, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(left + i) != word.charAt(right - i)) {
                return false;
            }
        }

        return true;
    }
}