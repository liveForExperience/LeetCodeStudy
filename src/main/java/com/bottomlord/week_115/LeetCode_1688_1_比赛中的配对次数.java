package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-23 08:36:12
 */
public class LeetCode_1688_1_比赛中的配对次数 {
    public int numberOfMatches(int n) {
        int count = 0;
        while (n != 1) {
            if(n % 2 == 0) {
                count += (n /= 2);
            } else {
                count += ((n /= 2) + 1);
            }
        }

        return count;
    }
}
