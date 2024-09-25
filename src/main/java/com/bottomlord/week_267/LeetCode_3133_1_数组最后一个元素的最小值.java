package com.bottomlord.week_267;

/**
 * @author chen yue
 * @date 2024-08-23 15:02:24
 */
public class LeetCode_3133_1_数组最后一个元素的最小值 {
    public long minEnd(int n, int x) {
        int m = n - 1, i = 0, j = 0;
        long ans = x;
        while ((m >> j) > 0) {
            if ((ans >> i & 1) == 0) {
                ans |= (long)(m >> j & 1) << i;
                j++;
            }

            i++;
        }

        return ans;
    }
}
