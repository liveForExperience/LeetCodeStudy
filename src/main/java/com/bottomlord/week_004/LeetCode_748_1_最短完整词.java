package com.bottomlord.week_004;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/8/2 13:19
 */
public class LeetCode_748_1_最短完整词 {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        char[] plateCs = licensePlate.toCharArray();
        int[] bucket = new int[26];

        for (char c: plateCs) {
            if (c <= 122 && c >= 97) {
                bucket[c - 'a']++;
            }

            if (c <= 90 && c >= 65) {
                bucket[c - 'A']++;
            }
        }

        List<String> ansList = new ArrayList<>();

        for (String word : words) {
            int[] tmpBucket = Arrays.copyOf(bucket, bucket.length);
            for (char c: word.toCharArray()) {
                if (c <= 122 && c >= 97) {
                    tmpBucket[c - 'a']--;
                }

                if (c <= 90 && c >= 65) {
                    tmpBucket[c - 'A']--;
                }
            }

            boolean find = true;
            for (int i : tmpBucket) {
                if (i > 0) {
                    find = false;
                    break;
                }
            }

            if (find) {
                ansList.add(word);
            }
        }

        String ans = "";
        if (!ansList.isEmpty()) {
            ans = ansList.get(0);
            for (String word: ansList) {
                if (word.length() < ans.length()) {
                    ans = word;
                }
            }
        }

        return ans;
    }
}