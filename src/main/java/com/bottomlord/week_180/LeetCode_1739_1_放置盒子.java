package com.bottomlord.week_180;

/**
 * @author chen yue
 * @date 2022-12-25 10:39:30
 */
public class LeetCode_1739_1_放置盒子 {
    public int minimumBoxes(int n) {
        int cur = 0, i = 0, j = 0;
        while (cur < n) {
            j++;
            i += j;
            cur += i;
        }

        if (cur == n) {
            return i;
        }

        cur -= i;
        i -= j;
        j = 0;

        while (cur < n) {
            j++;
            cur += j;
        }

        return i + j;
    }
}
