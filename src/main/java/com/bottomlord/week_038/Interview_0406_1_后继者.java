package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/3/25 8:09
 */
public class Interview_0406_1_后继者 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == p) {
                return i == list.size() - 1 ? null : list.get(i + 1);
            }
        }

        return null;
    }

    private void inOrder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }

        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
    }
}
