package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-30 14:26:51
 */
public class LeetCode_LCP11_2 {
    public int expectNumber(int[] scores) {
        boolean[] bucket = new boolean[1000001];
        int ans = 0;
        for (int score : scores) {
            if (!bucket[score]) {
                bucket[score] = true;
                ans++;
            }
        }

        return ans;
    }
}
