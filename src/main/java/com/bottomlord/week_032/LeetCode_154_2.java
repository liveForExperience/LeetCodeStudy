package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/16 13:48
 */
public class LeetCode_154_2 {
    public int findMin(int[] nums) {
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] > nums[tail]) {
                head = mid + 1;
            } else if (nums[mid] < nums[tail]) {
                tail = mid;
            } else {
                tail--;
            }
        }

        return nums[head];
    }
}
