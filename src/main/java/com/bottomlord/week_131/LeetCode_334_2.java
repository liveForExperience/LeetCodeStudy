package com.bottomlord.week_131;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-12 09:11:19
 */
public class LeetCode_334_2 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length, max = 1;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int l = 1, r = i + 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (f[mid] >= num) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            f[r] = num;
            max = Math.max(max, r);

            if (max >= 3) {
                return true;
            }
        }

        return false;
    }
}