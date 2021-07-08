package com.bottomlord.week_104;

/**
 * @author ChenYue
 * @date 2021/7/8 8:33
 */
public class LeetCode_930_3 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int left1 = 0, left2 = 0, n = nums.length, sum = 0, count = 0, sum1 = 0, sum2 = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (left1 <= right && sum1 < sum - goal) {
                sum1 += nums[left1];
                left1++;
            }

            while (left2 <= right && sum2 <= sum - goal) {
                sum2 += nums[left2];
                left2++;
            }

            count += left2 - left1;
        }

        return count;
    }
}
