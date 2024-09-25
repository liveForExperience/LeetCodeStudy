package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/21 13:04
 */
public class Interview_21_1_调整数组顺序使奇数位于偶数前面 {
    public int[] exchange(int[] nums) {
        int[] ans = new int[nums.length];
        int head = 0, tail = ans.length - 1;
        for (int num : nums) {
            if ((num & 1) == 1) {
                ans[head++] = num;
            } else {
                ans[tail--] = num;
            }
        }

        return ans;
    }
}
