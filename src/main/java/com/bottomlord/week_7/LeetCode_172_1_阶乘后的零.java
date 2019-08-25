package com.bottomlord.week_7;

public class LeetCode_172_1_阶乘后的零 {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
