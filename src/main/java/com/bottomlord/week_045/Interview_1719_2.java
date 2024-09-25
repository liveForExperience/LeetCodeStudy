package com.bottomlord.week_045;

import org.w3c.dom.ls.LSOutput;

/**
 * @author ChenYue
 * @date 2020/5/14 8:33
 */
public class Interview_1719_2 {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2, sum = (1 + n) * n / 2, total = 0;
        for (int num : nums) {
            total += num;
        }

        int twoSum = sum - total,
            mid = twoSum / 2;

        int firstSum = (1 + mid) * mid / 2,
                firstTotal = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid) {
                firstTotal += nums[i];
            } else {
                break;
            }
        }

        int firstNum = Math.abs(firstSum - firstTotal);

        return new int[]{firstNum, twoSum - firstNum};
    }
}
