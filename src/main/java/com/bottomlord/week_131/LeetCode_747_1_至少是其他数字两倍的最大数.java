package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-13 08:48:59
 */
public class LeetCode_747_1_至少是其他数字两倍的最大数 {
    public int dominantIndex(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int a = nums[0], b = nums[1], maxI, secondI;
        if (a >= b) {
            maxI = 0;
            secondI = 1;
        } else {
            maxI = 1;
            secondI = 0;
        }
        for (int i = 2; i < nums.length; i++) {
            int num = nums[i], max = nums[maxI], second = nums[secondI];
            if (num >= max) {
                secondI = maxI;
                maxI = i;
            } else if (num >= second) {
                secondI = i;
            }
        }

        return nums[maxI] >= 2 * nums[secondI] ? maxI : -1;
    }
}
