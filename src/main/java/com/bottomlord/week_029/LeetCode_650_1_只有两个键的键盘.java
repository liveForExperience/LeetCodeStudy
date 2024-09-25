package com.bottomlord.week_029;

/**
 * @author ThinkPad
 * @date 2020/1/25 20:29
 */
public class LeetCode_650_1_只有两个键的键盘 {
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }
}
