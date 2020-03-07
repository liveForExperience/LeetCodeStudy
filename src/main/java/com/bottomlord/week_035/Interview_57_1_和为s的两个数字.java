package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/7 14:51
 */
public class Interview_57_1_和为s的两个数字 {
    public int[] twoSum(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        int[] ans = new int[2];
        while (head < tail) {
            int sum = nums[head] + nums[tail];
            if (sum < target) {
                head++;
            } else if (sum > target) {
                tail--;
            } else {
                ans[0] = nums[head];
                ans[1] = nums[tail];
                break;
            }
        }

        return ans;
    }
}
