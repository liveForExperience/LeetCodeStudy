package com.bottomlord.week_7;

public class LeetCode_374_1_猜数字大小 {
    private int pick = 6;

    public int guessNumber(int n) {
        int head = 0, tail = n;
        while (head <= tail) {
            int mid = head + ((tail - head) >> 1);
            if (guess(mid) == 0) {
                return mid;
            }

            if (guess(mid) == 1) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return -1;
    }

    private int guess(int num) {
        return Integer.compare(num, pick);
    }
}
