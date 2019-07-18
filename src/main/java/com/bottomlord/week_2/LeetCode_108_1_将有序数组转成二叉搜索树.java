package com.bottomlord.week_2;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/18 18:25
 */
public class LeetCode_108_1_将有序数组转成二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right - left) / 2 + left;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, left, mid - 1);
        node.right = dfs(nums, mid + 1, right);
        return node;
    }
}
