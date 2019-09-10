package com.bottomlord.week_010;

public class LeetCode_1079_2 {
    public int numTilePossibilities(String tiles) {
        if (tiles.length() == 0) {
            return 0;
        }

        int[] bucket = new int[26];
        for (char c : tiles.toCharArray()) {
            bucket[c - 'A']++;
        }

        return backTrack(bucket);
    }

    private int backTrack(int[] bucket) {
        int result = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }
            result++;
            bucket[i]--;
            result += backTrack(bucket);
            bucket[i]++;
        }
        return result;
    }
}
