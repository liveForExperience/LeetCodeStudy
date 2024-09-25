package com.bottomlord.week_035;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/5 8:20
 */
public class Interview_54_1_二叉搜索树的第k大节点 {
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list.get(k);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.right, list);
        list.add(node.val);
        dfs(node.left, list);
    }
}
