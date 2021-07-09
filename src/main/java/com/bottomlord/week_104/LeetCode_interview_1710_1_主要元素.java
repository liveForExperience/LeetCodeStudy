package com.bottomlord.week_104;

/**
 * @author ChenYue
 * @date 2021/7/9 8:16
 */
public class LeetCode_interview_1710_1_主要元素 {
    public int majorityElement(int[] nums) {
        int candidate = -1, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }

        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return count > nums.length / 2 ? candidate : -1;
    }
}
