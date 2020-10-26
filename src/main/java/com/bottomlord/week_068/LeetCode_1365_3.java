package com.bottomlord.week_068;

/**
 * @author ChenYue
 * @date 2020/10/26 8:43
 */
public class LeetCode_1365_3 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] count = new int[101], ans = new int[len];
        for (int num : nums) {
            count[num]++;
        }

        int[] sums = new int[count.length];
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            sum += count[i];
            sums[i] = sum - count[i];
        }

        for (int i = 0; i < len; i++) {
            ans[i] = sums[nums[i]];
        }

        return ans;
    }
}
