package com.bottomlord.week_091;

/**
 * @author ChenYue
 * @date 2021/4/9 8:35
 */
public class LeetCode_154_2 {
    public int findMin(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] < nums[tail]) {
                tail = mid;
            } else if (nums[mid] > nums[tail]) {
                head = mid + 1;
            } else {
                tail--;
            }
        }

        return nums[head];
    }
}
