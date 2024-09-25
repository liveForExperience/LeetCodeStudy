package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/28 11:40
 */
public class LeetCode_868_1_二进制间距 {
    public int binaryGap(int N) {
        int max = 0;
        int count = 0;
        boolean find = false;
        while (N > 0) {
            if (find) {
                if ((N & 1) == 0) {
                    count++;
                } else {
                    max = Math.max(max, ++count);
                    count = 0;
                }
            } else {
                if ((N & 1) == 1) {
                    find = true;
                    count++;
                }
            }

            N >>= 1;
        }
        return max;
    }
}
