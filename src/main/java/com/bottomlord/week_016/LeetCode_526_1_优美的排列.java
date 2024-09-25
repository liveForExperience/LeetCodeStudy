package com.bottomlord.week_016;

public class LeetCode_526_1_优美的排列 {
    private int count = 0;
    public int countArrangement(int N) {
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i + 1;
        }
        backTrack(nums, 0);
        return count;
    }

    private void backTrack(int[] nums, int index) {
        if (index == nums.length) {
            count++;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            if (nums[i] % (i + 1) == 0 || (i + 1) % nums[i] == 0) {
                backTrack(nums, index + 1);
            }
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
