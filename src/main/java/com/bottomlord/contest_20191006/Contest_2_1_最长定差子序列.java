package com.bottomlord.contest_20191006;

import java.util.HashMap;
import java.util.Map;

public class Contest_2_1_最长定差子序列 {
    public int longestSubsequence(int[] arr, int difference) {
        int max = 1;
        Map<Integer, Integer> endIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr.length - 1 - i < max) {
                break;
            }

            if (!endIndexMap.containsKey(arr[i])) {
                for (int j = arr[arr.length - 1]; j >= i; j--) {
                    if (difference > 0) {
                        if (arr[j] % difference == arr[i]) {
                            endIndexMap.put(arr[i], j);
                        } else {
                            if (i == j) {
                                endIndexMap.put(arr[i], null);
                            }
                        }
                    } else {
                        if (arr[i] % Math.abs(difference) == arr[j]) {
                            endIndexMap.put(arr[i], j);
                        } else {
                            if (i == j) {
                                endIndexMap.put(arr[i], null);
                            }
                        }
                    }
                }
            } else {
                if (endIndexMap.get(arr[i]) == null) {
                    continue;
                }
            }

            int pre = arr[i], count =  1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] - pre == difference) {
                    count++;
                    pre = arr[j];
                }
            }
            max = Math.max(count, max);
        }

        return max;
    }
}
