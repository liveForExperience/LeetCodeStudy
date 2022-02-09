package com.bottomlord.week_135;

/**
 * @author chen yue
 * @date 2022-02-09 08:58:02
 */
public class LeetCode_offerII003_1_前n个数字二进制中1的个数 {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = count(i);
        }
        return ans;
    }

    private int count(int n) {
        int bit = 1, count = 0;
        while (bit <= n) {
            if ((bit & n) != 0) {
                count++;
            }

            bit <<= 1;
        }

        return count;
    }
}
