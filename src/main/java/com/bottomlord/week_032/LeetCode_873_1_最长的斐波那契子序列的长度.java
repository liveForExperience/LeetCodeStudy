package com.bottomlord.week_032;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/2/11 10:40
 */
public class LeetCode_873_1_最长的斐波那契子序列的长度 {
    public int lenLongestFibSubseq(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }

        int ans = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int x = A[i], y = A[j], sum = x + y, count = 2;

                while (set.contains(sum)) {
                    x = y;
                    y = sum;

                    sum = x + y;
                    count++;
                }

                ans = Math.max(ans, count);
            }
        }

        return ans < 3 ? 0 : ans;
    }
}
