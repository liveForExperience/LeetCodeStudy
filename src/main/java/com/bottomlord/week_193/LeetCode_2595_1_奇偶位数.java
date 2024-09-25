package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-20 21:43:35
 */
public class LeetCode_2595_1_奇偶位数 {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; i < 32; i++) {
            int index = i % 2 == 0 ? 0 : 1;
            if ((1 << i & n) != 0) {
                ans[index]++;
            }
        }
        return ans;
    }
}
