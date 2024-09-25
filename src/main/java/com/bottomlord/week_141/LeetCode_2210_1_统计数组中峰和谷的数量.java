package com.bottomlord.week_141;

public class LeetCode_2210_1_统计数组中峰和谷的数量 {
    public int countHillValley(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length - 1 && i >= 0; ) {
            int left = find(i, -1, nums),
                    right = find(i, 1, nums);

            if (left != -1 && right != -1) {
                if (nums[i] > nums[left] && nums[i] > nums[right]) {
                    count++;
                } else if (nums[i] < nums[left] && nums[i] < nums[right]) {
                    count++;
                }
            }

            i = right;
        }

        return count;
    }

    private int find(int index, int path, int[] nums) {
        int num = nums[index];
        for (int i = index + path; i < nums.length && i >= 0; i += path) {
            if (num != nums[i]) {
                return i;
            }
        }

        return -1;
    }
}
