package com.bottomlord.week_050;

/**
 * @author ChenYue
 * @date 2020/6/17 9:03
 */
public class LeetCode_45_2 {
    public int jump(int[] nums) {
        int count = 0, position = nums.length - 1;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}