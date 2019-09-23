package com.bottomlord.week_012;

public class LeetCode_791_1_自定义字符串排序 {
    public String customSortString(String S, String T) {
        int[] nums = new int[26];
        for (char c : T.toCharArray()) {
            nums[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            for (int i = 0; i < nums[c - 'a']; i++) {
                sb.append(c);
            }
            nums[c - 'a'] = 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                for (int j = 0; j < nums[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }

        return sb.toString();
    }
}
