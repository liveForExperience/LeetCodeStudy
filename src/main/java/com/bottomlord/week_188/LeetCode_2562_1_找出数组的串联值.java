package com.bottomlord.week_188;

/**
 * @author chen yue
 * @date 2023-02-13 18:52:43
 */
public class LeetCode_2562_1_找出数组的串联值 {
    public long findTheArrayConcVal(int[] nums) {
        int l = 0, r = nums.length - 1;
        long ans = 0;

        while (l <= r) {
            long num = 0;
            int index = 0;
            while (nums[r] > 0) {
                int cur = 1;
                for (int i = 0; i < index; i++) {
                    cur *= 10;
                }

                num = (nums[r] % 10L) * cur + num;
                nums[r] /= 10;
                index++;
            }

            while (nums[l] > 0) {
                int cur = 1;
                for (int i = 0; i < index; i++) {
                    cur *= 10;
                }

                num = (nums[l] % 10L) * cur + num;
                nums[l] /= 10;
                index++;
            }

            ans += num;
            l++;
            r--;
        }

        return ans;
    }
}
