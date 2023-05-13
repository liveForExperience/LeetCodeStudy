package com.bottomlord.week_200;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-05-13 14:03:57
 */
public class LeetCode_2670_1_找出不同元素数目差数组 {
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length, cnt = 0;
        int[] diff = new int[n];
        boolean[] memo = new boolean[51];
        for (int i = 0; i < n; i++) {
            if (!memo[nums[i]]) {
                memo[nums[i]] = true;
                cnt++;
            }

            boolean[] innerMemo = new boolean[51];
            int latterCnt = 0;
            for (int j = i + 1; j < n; j++) {
                if (!innerMemo[nums[j]]) {
                    latterCnt++;
                    innerMemo[nums[j]] = true;
                }
            }

            diff[i] = cnt - latterCnt;
        }

        return diff;
    }
}
