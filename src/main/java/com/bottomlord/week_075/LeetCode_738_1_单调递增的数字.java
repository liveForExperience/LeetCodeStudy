package com.bottomlord.week_075;

/**
 * @author ChenYue
 * @date 2020/12/15 8:27
 */
public class LeetCode_738_1_单调递增的数字 {
    public int monotoneIncreasingDigits(int N) {
        for (int i = N; i >= 0; i--) {
            if (isValid(i)) {
                return i;
            }
        }

        return 0;
    }

    private boolean isValid(int n) {
        int pre = n % 10;
        n /= 10;

        while (n > 0) {
            int cur = n % 10;
            if (cur > pre) {
                return false;
            }

            pre = cur;
            n /= 10;
        }

        return true;
    }
}
