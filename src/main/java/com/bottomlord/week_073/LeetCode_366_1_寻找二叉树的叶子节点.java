package com.bottomlord.week_073;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/12/2 10:32
 */
public class LeetCode_366_1_寻找二叉树的叶子节点 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        while (root != null) {
            List<Integer> list = new ArrayList<>();
            root = preOrder(root, list);
            ans.add(list);
        }

        return ans;
    }

    private TreeNode preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return null;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
            return null;
        }

        node.left = preOrder(node.left, list);
        node.right = preOrder(node.right, list);
        return node;
    }
}