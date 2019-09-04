package com.bottomlord.week_009;

public class LeetCode_633_1_平方数之和 {
    public boolean judgeSquareSum(int c) {
        int head = 0, tail = c;
        while (head <= tail) {
            if (isQuadraticSum(head) && isQuadraticSum(tail)) {
                return true;
            }

            head++;
            tail--;
        }

        return false;
    }

    private boolean isQuadraticSum(int sum) {
        int head = 0, tail = sum / 2 + 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            double pow = Math.pow(mid, 2);
            if (pow == sum) {
                return true;
            }

            if (pow < sum) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        return false;
    }
}
