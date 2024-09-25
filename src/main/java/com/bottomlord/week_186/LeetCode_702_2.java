package com.bottomlord.week_186;

import com.bottomlord.ArrayReader;

/**
 * @author chen yue
 * @date 2023-02-01 18:16:54
 */
public class LeetCode_702_2 {
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) {
            return 0;
        }

        int head = 1, tail = 1;
        while (reader.get(tail) < target) {
            head = tail;
            tail <<= 1;
        }

        if (reader.get(head) == Integer.MAX_VALUE) {
            return -1;
        }

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            int num = reader.get(mid);

            if (num == Integer.MAX_VALUE) {
                tail = mid - 1;
                continue;
            }

            if (num == target) {
                return mid;
            } else if (num < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return -1;
    }
}