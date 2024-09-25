package com.bottomlord.week_225;

/**
 * @author chen yue
 * @date 2023-10-31 14:10:25
 */
public class LeetCode_275_1_H指数II {
    public int hIndex(int[] citations) {
        int head = 0, n = citations.length, tail = n - 1;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            int h = citations[mid];
            if (h < n - mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return n - head;
    }
}
