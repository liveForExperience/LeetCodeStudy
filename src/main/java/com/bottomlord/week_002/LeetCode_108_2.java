package com.bottomlord.week_002;

import com.bottomlord.TreeNode;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/18 21:01
 */
public class LeetCode_108_2 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Stack<int []> arrStack = new Stack<>();
        arrStack.push(new int[]{0, nums.length - 1});

        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode root = new TreeNode(nums[(nums.length - 1) / 2]);
        nodeStack.push(root);

        while (!arrStack.isEmpty()) {
            TreeNode node = nodeStack.pop();

            int[] arr = arrStack.pop();
            int left = arr[0], right = arr[1];
            int mid = (right - left) / 2 + left;

            if (left <= mid - 1) {
                node.left = new TreeNode(nums[(mid - 1 - left) / 2 + left]);
                nodeStack.push(node.left);
                arrStack.push(new int[]{left, mid - 1});
            }

            if (mid + 1 <= right) {
                node.right = new TreeNode(nums[(right - mid - 1) / 2 + mid + 1]);
                nodeStack.push(node.right);
                arrStack.push(new int[]{mid + 1, right});
            }
        }

        return root;
    }
}
