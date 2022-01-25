package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-25 08:46:07
 */
public class LeetCode_1688_1_比赛中的配对次数 {
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            count += n / 2;
            n = n % 2 == 1 ? n / 2 + 1 : n / 2;
        }
        return count;
    }
}
