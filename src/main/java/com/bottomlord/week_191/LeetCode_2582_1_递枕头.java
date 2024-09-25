package com.bottomlord.week_191;

/**
 * @author chen yue
 * @date 2023-03-07 20:01:06
 */
public class LeetCode_2582_1_递枕头 {
    public int passThePillow(int n, int time) {
        int cur = 1;
        boolean flag = true;
        while (time-- > 0) {
            if (flag) {
                cur++;
                if (cur == n) {
                    flag = false;
                }
            } else {
                cur--;

                if (cur == 1) {
                    flag = true;
                }
            }
        }

        return cur;
    }
}
