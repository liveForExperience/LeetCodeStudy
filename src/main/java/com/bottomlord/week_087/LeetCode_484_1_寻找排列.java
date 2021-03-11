package com.bottomlord.week_087;

/**
 * @author ChenYue
 * @date 2021/3/10 8:26
 */
public class LeetCode_484_1_寻找排列 {
    public int[] findPermutation(String s) {
        int len = s.length();
        if (len == 0) {
            return new int[0];
        }

        int[] ans = new int[len + 1];
        char[] cs = s.toCharArray();
        boolean[] memo = new boolean[len + 2];

        backTrack(0, cs, ans, memo);
        return ans;
    }

    private boolean backTrack(int index, char[] cs, int[] ans, boolean[] memo) {
        if (index > cs.length) {
            return true;
        }

        for (int i = 1; i <= ans.length; i++) {
            if (memo[i]) {
                continue;
            }

            if (index == 0) {
                memo[i] = true;
                ans[index] = i;
                boolean result = backTrack(index + 1, cs, ans, memo);
                if (result) {
                    return true;
                }

                ans[index] = 0;
                memo[i] = false;

            } else {
                if (cs[index - 1] == 'I') {
                    if (i > ans[index - 1]) {
                        memo[i] = true;
                        ans[index] = i;
                        boolean result = backTrack(index + 1, cs, ans, memo);
                        if (result) {
                            return true;
                        }

                        memo[i] = false;
                        ans[index] = 0;
                    }
                } else {
                    if (i < ans[index - 1]) {
                        memo[i] = true;
                        ans[index] = i;
                        boolean result = backTrack(index + 1, cs, ans, memo);
                        if (result) {
                            return true;
                        }

                        memo[i] = false;
                        ans[index] = 0;
                    }
                }
            }
        }

        return false;
    }
}
