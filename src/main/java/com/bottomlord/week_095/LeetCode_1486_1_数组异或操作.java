package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/7 8:31
 */
public class LeetCode_1486_1_数组异或操作 {
    public int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= start + i * 2;
        }
        return ans;
    }
}
