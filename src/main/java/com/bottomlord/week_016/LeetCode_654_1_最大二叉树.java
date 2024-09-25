package com.bottomlord.week_016;

import com.bottomlord.TreeNode;

public class LeetCode_654_1_最大二叉树 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        if (start > end) {
            return null;
        }

        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }

        TreeNode node = new TreeNode(nums[index]);
        node.left = dfs(nums, start, index - 1);
        node.right = dfs(nums, index + 1, end);
        return node;
    }
}
