package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/13 19:42
 */
public class Interview_03_3 {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }

                int tmp = nums[i];
                nums[i] = nums[nums[i]];
                nums[tmp] = tmp;
            }
        }

        return 1;
    }
}
