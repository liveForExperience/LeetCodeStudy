package com.bottomlord.week_200;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-05-11 21:08:01
 */
public class LeetCode_971_1_翻转二叉树以匹配先序遍历 {

    private int index = 0;
    private int[] voyage;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (index >= voyage.length) {
            return;
        }

        if (node == null) {
            return;
        }

        if (list.size() == 1 && list.get(0) == -1) {
            return;
        }

        int val = node.val;
        if (val != voyage[index++]) {
            list.clear();
            list.add(-1);
            return;
        }

        int next = 0;
        if (node.left != null) {
            next = node.left.val;
        } else if (node.right != null) {
            next = node.right.val;
        }

        if (index >= voyage.length || next == 0) {
            return;
        }

        if (next != voyage[index]) {
            list.add(node.val);
            dfs(node.right, list);
            dfs(node.left, list);
        } else {
            dfs(node.left, list);
            dfs(node.right, list);
        }
    }
}
