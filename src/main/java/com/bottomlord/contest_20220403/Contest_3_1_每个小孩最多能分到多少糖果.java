package com.bottomlord.contest_20220403;

/**
 * @author chen yue
 * @date 2022-04-03 10:50:07
 */
public class Contest_3_1_每个小孩最多能分到多少糖果 {
    public int maximumCandies(int[] candies, long k) {
        long min = Integer.MAX_VALUE, sum = 0;
        for (int candy : candies) {
            sum += candy;
            min = Math.min(min, candy);
        }

        if (sum < k) {
            return 0;
        }

        long head = 1, tail = sum / k;
        while (head <= tail) {
            long mid = head + (tail - head) / 2;
            long cur = count(candies, k, mid);

            if (cur >= k) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return (int)tail;
    }

    private long count(int[] candies, long k, long target) {
        long cur = 0;
        for (int candy : candies) {
            cur += (long) candy / target;

            if (cur >= k) {
                return cur;
            }
        }
        return cur;
    }
}
