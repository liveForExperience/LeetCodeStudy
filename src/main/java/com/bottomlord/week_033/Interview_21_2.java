package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/21 13:14
 */
public class Interview_21_2 {
    public int[] exchange(int[] nums) {
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            if ((nums[head] & 1) == 1) {
                head++;
                continue;
            }

            int tmp = nums[tail];
            nums[tail] = nums[head];
            nums[head] = tmp;

            tail--;
        }

        return nums;
    }
}