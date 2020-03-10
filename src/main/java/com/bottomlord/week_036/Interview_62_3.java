package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/10 20:50
 */
public class Interview_62_3 {
    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}