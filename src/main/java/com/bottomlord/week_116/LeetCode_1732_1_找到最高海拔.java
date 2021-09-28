package com.bottomlord.week_116;

/**
 * @author chen yue
 * @date 2021-09-28 21:35:17
 */
public class LeetCode_1732_1_找到最高海拔 {
    public int largestAltitude(int[] gain) {
        int cur = 0, max = 0;
        for (int num : gain) {
            cur += num;
            max = Math.max(cur, max);
        }

        return max;
    }
}
