package com.bottomlord.week_063;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/16 8:41
 */
public class LeetCode_272_1_最接近的二叉搜索树值II {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> ans = new LinkedList<>();
        dfs(root, target, k, ans);
        return ans;
    }

    private void dfs(TreeNode node, double target, int k, LinkedList<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, target, k, list);
        if (list.size() < k) {
            list.addLast(node.val);
        } else if (Math.abs(node.val - target) < Math.abs(list.getFirst() - target)) {
            list.removeFirst();
            list.addLast(node.val);
        } else {
            return;
        }
        dfs(node.right, target, k, list);
    }
}
