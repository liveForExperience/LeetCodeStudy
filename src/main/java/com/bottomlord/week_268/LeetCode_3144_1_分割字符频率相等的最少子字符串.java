package com.bottomlord.week_268;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2024-08-28 15:00:06
 */
public class LeetCode_3144_1_分割字符频率相等的最少子字符串 {
    private String s;
    private Set<String> balanceStrSet;
    private int[] memo;

    public int minimumSubstringsInPartition(String s) {
        this.s = s;
        this.balanceStrSet = new HashSet<>();
        this.memo = new int[s.length() + 1];
        return dfs(0);
    }

    private int dfs(int index) {
        if (index == s.length()) {
            return 0;
        }

        if (memo[index] != 0) {
            return memo[index];
        }

        int min = s.length() - index;
        for (int i = index; i < s.length(); i++) {
            if (!isBalance(index, i)) {
                continue;
            }

            if (memo[i + 1] != 0) {
                min = Math.min(min, memo[i + 1] + 1);
                continue;
            }

            memo[i + 1] = dfs(i + 1);
            min = Math.min(min, memo[i + 1] + 1);
        }

        memo[index] = min;
        return min;
    }

    private boolean isBalance(int start, int end) {
        String str = s.substring(start, end + 1);
        if (balanceStrSet.contains(str)) {
            return true;
        }

        int[] bucket = new int[26];
        for (int i = start; i <= end; i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        int target = -1;
        for (int num : bucket) {
            if (num == 0) {
                continue;
            }

            if (target == -1) {
                target = num;
                continue;
            }

            if (num != target) {
                return false;
            }
        }

        balanceStrSet.add(str);
        return true;
    }

    public static void main(String[] args) {
        LeetCode_3144_1_分割字符频率相等的最少子字符串 t = new LeetCode_3144_1_分割字符频率相等的最少子字符串();
        System.out.println(t.minimumSubstringsInPartition("abababaccddb"));
    }
}
