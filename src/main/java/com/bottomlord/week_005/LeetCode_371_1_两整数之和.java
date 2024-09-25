package com.bottomlord.week_005;

/**
 * @author LiveForExperience
 * @date 2019/8/6 20:22
 */
public class LeetCode_371_1_两整数之和 {
    public int getSum(int a, int b) {
        int ans = a ^ b, up = a & b, tmp;

        while (up != 0) {
            up <<= 1;
            tmp = ans;
            ans ^= up;
            up &= tmp;
        }

        return ans;
    }
}
