package com.bottomlord.week_096;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/5/10 9:47
 */
public class LeetCode_760_1_找出变位映射 {
    public int[] anagramMappings(int[] A, int[] B) {
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    ans[i] = j;
                }
            }
        }
        return ans;
    }
}
