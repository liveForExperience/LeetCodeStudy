package com.bottomlord.week_025;

/**
 * @author ThinkPad
 * @date 2019/12/29 16:41
 */
public class LeetCode_481_2 {
    public int magicalString(int n) {
        if (n == 0) {
            return 0;
        }

        if (n <= 3) {
            return 1;
        }

        int p1 = 2, p2 = 2, ans = 1;
        boolean[] bs = new boolean[n];
        bs[0] = true;
        bs[1] = bs[2] = false;

        while (true) {
            boolean tmp = bs[p1];
            int time = bs[p2++] ? 1 : 2;

            for (int i = 0 ; i < time; i++) {
                bs[++p1] = !tmp;
                if (!tmp) {
                    ans++;
                }

                if (p1 == n - 1) {
                    return ans;
                }
            }
        }
    }
}