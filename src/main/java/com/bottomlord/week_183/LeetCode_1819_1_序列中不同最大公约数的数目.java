package com.bottomlord.week_183;

/**
 * @author chen yue
 * @date 2023-01-14 22:04:13
 */
public class LeetCode_1819_1_序列中不同最大公约数的数目 {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        boolean[] hasNum = new boolean[max + 1];
        for (int num : nums) {
            hasNum[num] = true;
        }

        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int gcd = 0;
            for (int j = i; j <= max; j += i) {
                if (!hasNum[j]) {
                    continue;
                }

                if (gcd == 0) {
                    gcd = j;
                } else {
                    gcd = gcd(j, gcd);
                }

                if (gcd == i) {
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
