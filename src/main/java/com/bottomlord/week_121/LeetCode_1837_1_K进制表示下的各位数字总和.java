package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-06 14:37:45
 */
public class LeetCode_1837_1_K进制表示下的各位数字总和 {
    public int sumBase(int n, int k) {
        int ans = 0;
        while (n > 0) {
            ans += n % k;
            n /= k;
        }
        return ans;
    }
}
