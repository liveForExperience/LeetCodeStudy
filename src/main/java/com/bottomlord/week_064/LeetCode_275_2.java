package com.bottomlord.week_064;

/**
 * @author ChenYue
 * @date 2020/9/21 8:37
 */
public class LeetCode_275_2 {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int mid, head = 0, tail = len - 1;

        while (head <= tail) {
            mid = head + (tail - head) / 2;

            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return len - head;
    }
}