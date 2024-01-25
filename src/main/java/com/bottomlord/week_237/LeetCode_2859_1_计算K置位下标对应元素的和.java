package com.bottomlord.week_237;

import com.bottomlord.LeetCodeUtils;

import java.util.List;

/**
 * @author chen yue
 * @date 2024-01-25 18:12:57
 */
public class LeetCode_2859_1_计算K置位下标对应元素的和 {
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            if (check(i, k)) {
                sum += num;
            }
        }
        return sum;
    }

    private boolean check(int num, int k) {
        int cnt = 0;
        while (num > 0) {
            num = num & (num - 1);
            cnt++;
        }

        return cnt == k;
    }
}
