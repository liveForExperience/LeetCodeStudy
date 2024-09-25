package com.bottomlord.week_045;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * @author ChenYue
 * @date 2020/5/14 8:14
 */
public class Interview_1719_1_消失的两个数字 {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2, sum = (1 + n) * n / 2, total = 0;
        for (int num : nums) {
            total += num;
        }

        int mid = (sum - total) / 2;

        int firstSum = (1 + mid) * mid / 2,
            secondSum = sum - (mid + 1 + n) * (n - mid) / 2,
            firstTotal = 0, secondTotal = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid) {
                firstTotal += nums[i];
            } else {
                secondTotal += nums[i];
            }
        }

        return new int[]{firstSum - firstTotal, secondSum - secondTotal};
    }
}
