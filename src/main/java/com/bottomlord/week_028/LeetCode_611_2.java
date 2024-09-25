package com.bottomlord.week_028;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/19 8:58
 */
public class LeetCode_611_2 {
    public int triangleNumber(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int c = nums[i] + nums[j], head = j + 1, tail = nums.length - 1;
                while (head <= tail) {
                    int mid = head + (tail - head) / 2;
                    if (nums[mid] >= c) {
                        tail = mid - 1;
                    } else {
                        head = mid + 1;
                    }
                }

                ans += head - j - 1;
            }
        }

        return ans;
    }
}