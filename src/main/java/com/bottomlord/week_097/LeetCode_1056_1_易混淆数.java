package com.bottomlord.week_097;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/5/20 9:11
 */
public class LeetCode_1056_1_易混淆数 {
    public boolean confusingNumber(int N) {
        int[] reverses = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        int n = 0, o = N;
        while (N != 0) {
            int num = N % 10;
            if (reverses[num] == -1) {
                return false;
            }
            N /= 10;
            n = n * 10 + reverses[num];
        }

        return n != o;
    }
}
