package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-25 11:20:31
 */
public class LeetCode_172_2 {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}