package com.bottomlord.week_113;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-09-06 11:46:33
 */
public class LeetCode_1608_1_特殊数组的特征值 {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length, count = 0, n = -1;
        for (int i = 0; i < len; i++) {
            int num;
            if (i == 0) {
                num = 0;
            } else {
                num = nums[i - 1] + 1;
            }

            while (num <= nums[i]) {
                if (num == len - i) {
                    count++;
                    n = num;
                }

                if (count > 1) {
                    return -1;
                }

                num++;
            }
        }

        return count == 1 ? n : -1;
    }
}
