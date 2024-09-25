package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/7 9:58
 */
public class LeetCode_1486_2 {
    public int xorOperation(int n, int start) {
        int e = n & start & 1, s = start >> 1;
        int xor = sumXor(s - 1) ^ sumXor(s + n - 1);
        return xor << 1 | e;
    }

    private int sumXor(int x) {
        if (x % 4 == 0) {
            return x;
        }
        if (x % 4 == 1) {
            return 1;
        }
        if (x % 4 == 2) {
            return x + 1;
        }
        return 0;
    }
}
