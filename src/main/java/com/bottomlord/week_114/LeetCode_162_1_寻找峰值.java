package com.bottomlord.week_114;

/**
 * @author chen yue
 * @date 2021-09-15 09:01:14
 */
public class LeetCode_162_1_寻找峰值 {
    public int findPeakElement(int[] nums) {
        return binarySearch(0, nums.length - 1, nums);
    }

    private int binarySearch(int head, int tail, int[] nums) {
        if (head == tail) {
            return head;
        }

        int mid = head + (tail - head) / 2;
        return  (nums[mid] < nums[mid + 1]) ? binarySearch(mid + 1, tail, nums) : binarySearch(head, mid, nums);
    }
}
