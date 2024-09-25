package com.bottomlord.week_008;

public class LeetCode_278_1_第一个错误的版本 {
    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int head = 2, tail = n;
            while (head <= tail) {
                int mid = head + (tail - head) / 2;
                if (!isBadVersion(mid - 1) && isBadVersion(mid)) {
                    return mid;
                }

                if (isBadVersion(mid) && isBadVersion(mid - 1)) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            }

            return 0;
        }
    }
}
