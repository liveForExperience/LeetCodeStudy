package com.bottomlord.week_186;

import com.bottomlord.ArrayReader;

/**
 * @author chen yue
 * @date 2023-02-01 15:56:39
 */
public class LeetCode_702_1_搜索长度未知的有序数组 {
    public int search(ArrayReader reader, int target) {
        int head = 0, tail = 9999;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            int num = reader.get(mid);

            if (num == Integer.MAX_VALUE) {
                tail--;
                continue;
            }

            if (num == target) {
                return mid;
            } else if (num > target) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return -1;
    }
}
