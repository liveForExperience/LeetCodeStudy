package com.bottomlord.week_233;

/**
 * @author chen yue
 * @date 2023-12-26 19:10:52
 */
public class LeetCode_1954_1_收集足够苹果的最小花园周长 {
    public long minimumPerimeter(long neededApples) {
        long head = 1, tail = 100000, ans = -1;

        while (head <= tail) {
            long mid = head + (tail - head) / 2,
                    target = 2 * mid * (mid + 1) * (2 * mid + 1);

            if (target >= neededApples) {
                ans = mid * 8;
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return ans;
    }
}
