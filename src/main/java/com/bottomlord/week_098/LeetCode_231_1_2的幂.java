package com.bottomlord.week_098;

public class LeetCode_231_1_2的幂 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        int num = n, count = 0;
        while (num != 0) {
            num &= (num - 1);
            count++;
        }
        return count == 1;
    }
}
