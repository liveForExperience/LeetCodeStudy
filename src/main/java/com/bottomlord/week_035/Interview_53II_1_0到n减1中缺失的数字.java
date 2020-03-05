package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/4 19:40
 */
public class Interview_53II_1_0到n减1中缺失的数字 {
    public int missingNumber(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1;

        while (head <= tail) {
            if ((len & 1) == 1 && nums[head] == nums[tail]) {
                break;
            }

            if (nums[head] + nums[tail] != len) {
                return nums[head] + nums[tail] < len ? len - nums[head] : len - nums[tail];
            }
            head++;
            tail--;
        }

        return -1;
    }
}
