package com.bottomlord.week_232;

/**
 * @author chen yue
 * @date 2023-12-18 16:20:59
 */
public class LeetCode_162_2 {
    public int findPeakElement(int[] nums) {
        return binarySearch(0, nums.length - 1, nums);
    }

    private int binarySearch(int head, int tail, int[] nums) {
        if (head == tail) {
            return head;
        }

        int mid = head + (tail - head) / 2;
        return nums[mid] > nums[mid + 1] ? binarySearch(head, mid, nums) : binarySearch(mid + 1, tail, nums);
    }
}