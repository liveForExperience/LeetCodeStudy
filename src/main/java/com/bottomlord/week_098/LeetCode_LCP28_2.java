package com.bottomlord.week_098;

import java.util.Arrays;

public class LeetCode_LCP28_2 {
    public int purchasePlans(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1, count = 0;
        Arrays.sort(nums);
        while (head < tail) {
            if (nums[head] + nums[tail] > target) {
                tail--;
            } else {
                count += tail - head;
                head++;
            }

            count %= 1000000007;
        }

        return count;
    }
}
