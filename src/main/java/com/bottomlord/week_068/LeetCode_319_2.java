package com.bottomlord.week_068;

/**
 * @author ChenYue
 * @date 2020/10/28 9:00
 */
public class LeetCode_319_2 {
    public int bulbSwitch(int n) {
        if (n <= 1) {
            return n;
        }

        int ans = 1;
        while (ans * ans <= n) {
            ans++;
        }
        return ans - 1;
    }
}
