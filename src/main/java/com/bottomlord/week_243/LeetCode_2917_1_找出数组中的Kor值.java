package com.bottomlord.week_243;

/**
 * @author chen yue
 * @date 2024-03-06 19:16:34
 */
public class LeetCode_2917_1_找出数组中的Kor值 {
    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                if ((num & (1 << i)) != 0) {
                    cnt++;
                }

                if (cnt >= k) {
                    break;
                }
            }

            if (cnt >= k) {
                ans += (1 << i);
            }
        }

        return ans;
    }
}
