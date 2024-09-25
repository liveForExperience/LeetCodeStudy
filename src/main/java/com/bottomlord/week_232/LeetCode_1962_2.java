package com.bottomlord.week_232;

/**
 * @author chen yue
 * @date 2023-12-23 14:36:03
 */
public class LeetCode_1962_2 {
    public int minStoneSum(int[] piles, int k) {
        int max = 0, sum = 0;
        for (int pile : piles) {
            max = Math.max(pile, max);
            sum += pile;
        }

        int[] bucket = new int[max + 1];
        for (int pile : piles) {
            bucket[pile]++;
        }

        while (k-- > 0) {
            while (max >= 0 && bucket[max] == 0) {
                max--;
            }

            if (max < 0) {
                break;
            }

            int minus = max / 2;
            sum -= minus;
            bucket[max]--;
            bucket[max - minus]++;
        }

        return sum;
    }
}