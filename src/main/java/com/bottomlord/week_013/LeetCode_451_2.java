package com.bottomlord.week_013;

import java.util.Arrays;

public class LeetCode_451_2 {
    public String frequencySort(String s) {
        char[] cs = s.toCharArray();
        int[] bucket = new int[256];
        for (char c : cs) {
            bucket[c]++;
        }

        Integer[] indexs = new Integer[256];
        for (int i = 0; i < indexs.length; i++) {
            indexs[i] = i;
        }

        Arrays.sort(indexs, (i, j) -> bucket[j] - bucket[i]);

        StringBuilder sb = new StringBuilder();
        for (int index : indexs) {
            if (bucket[index] == 0) {
                break;
            }

            for (int i = 0; i < bucket[index]; i++) {
                sb.append((char)index);
            }
        }

        return sb.toString();
    }
}