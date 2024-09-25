package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-16 13:17:05
 */
public class LeetCode_offerII68_1_查找插入位置 {
    public int searchInsert(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num < target) {
                head = mid;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
