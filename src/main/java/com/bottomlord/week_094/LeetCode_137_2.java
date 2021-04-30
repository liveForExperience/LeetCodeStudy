package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/30 8:51
 */
public class LeetCode_137_2 {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num & (1 << i)) != 0) ? 1 : 0;
            }

            ans |= (total % 3) << i;
        }
        return ans;
    }
}
