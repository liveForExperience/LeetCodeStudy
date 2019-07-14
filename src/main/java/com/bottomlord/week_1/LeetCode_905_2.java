package com.bottomlord.week_1;

/**
 * @author LiveForExperience
 * @date 2019/7/14 14:56
 */
public class LeetCode_905_2 {
    public int[] sortArrayByParity(int[] A) {
        int[] ans = new int[A.length];

        int even = 0, odd = A.length - 1;

        for (int num: A) {
            if (num % 2 == 0) {
                ans[even++] = num;
            } else {
                ans[odd--] = num;
            }
        }

        return ans;
    }
}