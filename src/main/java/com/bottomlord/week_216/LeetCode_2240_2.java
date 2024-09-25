package com.bottomlord.week_216;

/**
 * @author chen yue
 * @date 2023-09-02 14:18:25
 */
public class LeetCode_2240_2 {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long sum = 0;
        for (long i = 0; i * cost1 <= total; i++) {
            sum += binarySearch(total - i * cost1, cost2) + 1;
        }
        return sum;
    }

    private long binarySearch(long target, int cost2) {
        long head = 0, tail = target, ans = -1;
        while (head <= tail) {
            long mid = head + (tail - head) / 2, cur = mid * cost2;

            if (cur <= target) {
                head = mid + 1;
                ans = mid;
            } else {
                tail = mid - 1;
            }
        }

        return ans;
    }
}
