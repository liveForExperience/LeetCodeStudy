package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/15 21:28
 */
public class LeetCode_944_2 {
    public int minDeletionSize(String[] A) {
        int count = 0;

        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j].charAt(i) < A[j - 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}