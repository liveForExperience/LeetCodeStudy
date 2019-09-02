package com.bottomlord.week_007;

public class LeetCode_367_1_有效的完全平方数 {
    public boolean isPerfectSquare(int num) {
        int head = 0, tail = num / 2 + 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if ((long) mid * mid == num) {
                return true;
            }

            if ((long) mid * mid > num) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return false;
    }
}
