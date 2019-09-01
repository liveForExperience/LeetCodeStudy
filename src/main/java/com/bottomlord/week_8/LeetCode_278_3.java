package com.bottomlord.week_8;

public class LeetCode_278_3 {
    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int head = 0, tail = n;
            while (head < tail) {
                int mid = head + (tail - head) / 2;
                if (isBadVersion(mid)) {
                    tail = mid;
                } else {
                    head = mid + 1;
                }
            }
            return head;
        }
    }
}
