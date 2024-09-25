package com.bottomlord.week_154;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-06-26 10:18:01
 */
public class LeetCode_698_1_划分为K个相等的子集 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;
        for (int num : nums) {
            if (num > target) {
                return false;
            }
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length / 2; i++) {
            int tmp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = tmp;
        }

        return backTrack(nums, new int[k], 0, target);
    }

    private boolean backTrack(int[] nums, int[] bucket, int index, int target) {
        if (index == nums.length) {
            return true;
        }

        int num = nums[index];
        for (int i = 0; i < bucket.length; i++) {
            if (i > 0 && bucket[i] == bucket[i - 1]) {
                continue;
            }

            if (bucket[i] + num > target) {
                continue;
            }

            bucket[i] += num;
            boolean flag = backTrack(nums, bucket, index + 1, target);
            if (flag) {
                return true;
            }
            bucket[i] -= num;

            if (i == 0) {
                break;
            }
        }

        return false;
    }
}
