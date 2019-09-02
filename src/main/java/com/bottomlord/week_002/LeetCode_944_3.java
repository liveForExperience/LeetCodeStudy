package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/15 21:37
 */
public class LeetCode_944_3 {
    public int minDeletionSize(String[] A) {
        int count = 0;

        for (int i = 0; i < A[0].length(); i++) {
            char pre = 0;
            for (String s : A) {
                char cur = s.charAt(i);

                if (cur < pre) {
                    count++;
                    break;
                }

                pre = cur;
            }
        }

        return count;
    }
}
