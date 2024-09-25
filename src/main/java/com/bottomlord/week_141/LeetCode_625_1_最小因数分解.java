package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-21 10:09:16
 */
public class LeetCode_625_1_最小因数分解 {
    public int smallestFactorization(int num) {
        long ans = 0, index = 0, ten = 1;
        while (num >= 10) {
            long cur = 0;
            for (int i = 9; i > 1; i--) {
                if (num % i == 0) {
                    cur = i;
                    break;
                }
            }

            if (cur == 0) {
                return 0;
            }

            ten *= index == 0 ? 1 : 10;
            ans += index == 0 ? cur : ten * cur;
            index++;
            num /= cur;
        }

        ans += index == 0 ? num : ten * 10 * num;

        return ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE ? 0 : (int) ans;
    }
}
