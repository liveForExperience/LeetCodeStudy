package com.bottomlord.week_009;

public class LeetCode_633_2 {
    public boolean judgeSquareSum(int c) {
        int head = 0, tail = (int)Math.sqrt(c);
        while (head <= tail) {
            int sum = head * head + tail * tail;
            if (sum == c) {
                return true;
            }

            if (sum < c) {
                head++;
            } else {
                tail--;
            }
        }

        return false;
    }
}
