package com.bottomlord.week_013;

import java.util.Arrays;

public class LeetCode_451_3 {
    public String frequencySort(String s) {
        char[] cs = s.toCharArray();
        int[] bucket = new int[256];

        for (char c : cs) {
            bucket[c]++;
        }

        int[] copy = bucket.clone();
        Arrays.sort(bucket);

        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (i != bucket.length - 1 && bucket[i] == bucket[i + 1]) {
                continue;
            }

            if (bucket[i] == 0) {
                break;
            }

            for (int j = 0; j < copy.length; j++) {
                if (bucket[i] == copy[j]) {
                    while (copy[j]-- > 0) {
                        sb.append((char)j);
                    }
                }
            }
        }

        return sb.toString();
    }
}