package com.bottomlord.week_163;

/**
 * @author chen yue
 * @date 2022-08-28 23:27:10
 */
public class LeetCode_793_1_阶乘函数后K个零 {
    public int preimageSizeFZF(int k) {
        long head = 0, tail = 5L * k;
        while (head <= tail) {
            long mid = head + (tail - head) / 2;
            long n = 5L, num = 0L;
            while (n <= mid) {
                num += mid / n;
                n *= 5;
            }

            if (num == k) {
                return 5;
            } else if (num < k) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return 0;
    }
}
