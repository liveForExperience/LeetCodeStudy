package com.bottomlord.week_103;

import java.util.Arrays;

public class LeetCode_451_2 {
    public String frequencySort(String s) {
        int[][] bucket = new int[256][2];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            bucket[c][0] = c;
            bucket[c][1]++;
        }

        Arrays.sort(bucket, (x, y) -> y[1] - x[1]);
        StringBuilder sb = new StringBuilder();
        for (int[] arr : bucket) {
            if (arr[1] == 0) {
                break;
            }

            while (arr[1]-- > 0) {
                sb.append((char) arr[0]);
            }
        }

        return sb.toString();
    }
}
