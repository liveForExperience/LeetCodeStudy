package com.bottomlord.week_096;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/5/10 10:32
 */
public class LeetCode_760_2 {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            mapping.put(B[i], i);
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = mapping.get(A[i]);
        }
        return A;
    }
}
