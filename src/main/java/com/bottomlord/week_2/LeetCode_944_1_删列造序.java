package com.bottomlord.week_2;

/**
 * @author LiveForExperience
 * @date 2019/7/15 20:58
 */
public class LeetCode_944_1_删列造序 {
    public int minDeletionSize(String[] A) {
        char pre = 0;
        boolean flag = false;
        int count = 0;

        for (int i = 0; i < A[0].length(); i++) {
            for (String s : A) {
                if (s.charAt(i) < pre) {
                    flag = true;
                    break;
                }

                pre = s.charAt(i);
            }

            if (flag) {
                count++;
            }

            flag = false;
            pre = 0;
        }

        return count;
    }
}
