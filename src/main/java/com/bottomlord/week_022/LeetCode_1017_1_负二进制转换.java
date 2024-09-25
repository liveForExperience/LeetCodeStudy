package com.bottomlord.week_022;

public class LeetCode_1017_1_负二进制转换 {
    public String baseNeg2(int N) {
        if (N == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            if (N % 2 == 0) {
                N /= (-2);
                sb.insert(0, 0);
            } else {
                N = (N - 1) / (-2);
                sb.insert(0, 1);
            }
        }

        return sb.toString();
    }
}
