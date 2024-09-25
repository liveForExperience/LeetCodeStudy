package com.bottomlord.week_238;

import com.bottomlord.LeetCodeUtils;

/**
 * @author chen yue
 * @date 2024-01-31 14:27:10
 */
public class LeetCode_2670_1_找出不同元素数目差数组 {
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length, cnt = 0;
        boolean[] memo = new boolean[51];
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums[i], innerCnt = 0;
            if (!memo[num]) {
                cnt++;
                memo[num] = true;
            }

            boolean[] innerMemo = new boolean[51];
            for (int j = i + 1; j < n; j++) {
                int innerNum = nums[j];
                if (!innerMemo[innerNum]) {
                    innerCnt++;
                    innerMemo[innerNum] = true;
                }
            }

            diff[i] = cnt - innerCnt;
        }

        return diff;
    }
}
