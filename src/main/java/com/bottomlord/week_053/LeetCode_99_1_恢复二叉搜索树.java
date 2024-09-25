package com.bottomlord.week_053;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/7/6 8:56
 */
public class LeetCode_99_1_恢复二叉搜索树 {
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);

        TreeNode node1 = list.get(list.size() - 1);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                node1 = list.get(i);
            }
        }

        TreeNode node2 = list.get(0);
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).val < list.get(i - 1).val) {
                node2 = list.get(i);
            }
        }

        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    private void inorder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }

        inorder(node.left, list);
        list.add(node);
        inorder(node.right, list);
    }
}
