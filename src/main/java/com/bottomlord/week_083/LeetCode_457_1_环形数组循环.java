package com.bottomlord.week_083;

/**
 * @author ChenYue
 * @date 2021/2/13 22:39
 */
public class LeetCode_457_1_环形数组循环 {
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return true;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                continue;
            }

            int slow = i, fast = next(nums, slow, len), val = nums[slow];
            while (val * nums[fast] > 0 && val * next(nums, fast, len) > 0) {
                if (slow == fast) {
                    if (slow == next(nums, slow, len)) {
                        break;
                    }

                    return true;
                }

                slow = next(nums, slow, len);
                fast = next(nums, next(nums, fast, len), len);
            }

            slow = i;
            while (val * nums[slow] > 0) {
                nums[slow] = 0;
                slow = next(nums, slow, len);
            }
        }

        return false;
    }

    private int next(int[] nums, int index, int len) {
        return ((index + nums[index] + len) % len + len) % len;
    }
}
