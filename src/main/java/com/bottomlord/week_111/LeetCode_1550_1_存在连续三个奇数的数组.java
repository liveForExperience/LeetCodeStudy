package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-28 11:03:32
 */
public class LeetCode_1550_1_存在连续三个奇数的数组 {
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                count = 0;
            } else {
                count++;
            }

            if (count == 3) {
                return true;
            }
        }

        return false;
    }
}
