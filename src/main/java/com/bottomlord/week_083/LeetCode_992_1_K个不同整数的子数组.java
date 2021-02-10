package com.bottomlord.week_083;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/2/9 8:36
 */
public class LeetCode_992_1_K个不同整数的子数组 {
    public int subarraysWithKDistinct(int[] A, int K) {
        Set<Integer> memo = new HashSet<>();
        int len = A.length, ans = 0;

        for (int i = 0; i < len; i++) {
            int index1 = i;
            while (index1 >= 0) {
                memo.add(A[index1]);
                if (memo.size() == K) {
                    break;
                }
                index1--;
            }

            int index2 = index1;
            while (index2 >= 0) {
                if (memo.contains(A[index2 - 1])) {
                    index2--;
                } else {
                    break;
                }
            }

            if (memo.size() == K) {
                ans += index1 - index2 + 1;
            }

            memo.clear();
        }

        return ans;
    }
}
