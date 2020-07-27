package com.bottomlord.week_055;

/**
 * @author ChenYue
 * @date 2020/7/27 17:23
 */
public class LeetCode_410_2 {
    public int splitArray(int[] nums, int m) {
        int head = Integer.MIN_VALUE, tail = 0;

        for (int num : nums) {
            head = Math.max(head, num);
            tail += num;
        }

        while (head < tail) {
            int mid = (tail - head) / 2 + head, count = 1, total = 0;

            for (int num : nums) {
                total += num;
                if (total > mid) {
                    count++;
                    total = num;
                }

                if (count > m) {
                    break;
                }
            }

            if (count > m) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
