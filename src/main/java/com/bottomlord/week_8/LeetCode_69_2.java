package com.bottomlord.week_8;

public class LeetCode_69_2 {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int head = 0, tail = x / 2 + 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (mid <= x / mid && mid + 1 > x / (mid + 1)) {
                return mid;
            }

            if (mid < x / mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return 0;
    }
}