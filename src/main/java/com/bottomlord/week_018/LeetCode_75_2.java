package com.bottomlord.week_018;

public class LeetCode_75_2 {
    public void sortColors(int[] nums) {
        int head = 0, cur = 0, tail = nums.length - 1;
        while (cur < tail) {
            if (nums[cur] == 0) {
                swap(nums, head, cur);
                head++;
            } else if (nums[cur] == 2) {
                swap(nums, tail, cur);
                tail--;
            }

            cur++;
        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}