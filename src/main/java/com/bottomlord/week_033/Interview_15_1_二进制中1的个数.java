package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/18 12:54
 */
public class Interview_15_1_二进制中1的个数 {
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= (n - 1);
            ans++;
        }
        return ans;
    }
}
