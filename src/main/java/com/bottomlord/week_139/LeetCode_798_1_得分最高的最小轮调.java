package com.bottomlord.week_139;

/**
 * @author chen yue
 * @date 2022-03-09 09:01:47
 */
public class LeetCode_798_1_得分最高的最小轮调 {
    public int bestRotation(int[] nums) {
        int len = nums.length;
        int[] indexes = new int[len];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }

        int max = 0, ans = 0;
        for (int time = 0; time < len; time++) {
            int count = doCount(nums, indexes);
            if (count > max) {
                max = count;
                ans = time;
            }
            arrIncrease(indexes);
        }

        return ans;
    }

    private int doCount(int[] nums, int[] indexes) {
        int len = nums.length, count = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= indexes[i]) {
                count++;
            }
        }

        return count;
    }

    private void arrIncrease(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (nums[i] + len - 1) % len;
        }
    }
}
