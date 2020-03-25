package com.bottomlord.week_038;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/3/25 8:50
 */
public class Interview_0409_1_二叉搜索树序列 {
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            ans.add(new LinkedList<>());
            return ans;
        }

        LinkedList<Integer> path = new LinkedList<>();
        path.add(root.val);

        backTrack(root, new LinkedList<>(), path, ans);
        return ans;
    }

    private void backTrack(TreeNode node, LinkedList<TreeNode> queue, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            queue.add(node.left);
        }

        if (node.right != null) {
            queue.add(node.right);
        }

        if (queue.isEmpty()) {
            ans.add(new LinkedList<>(path));
        }

        for (int i = 0; i < queue.size(); i++) {
            TreeNode tmp = queue.remove(i);
            path.add(tmp.val);
            backTrack(tmp, new LinkedList<>(queue), path, ans);
            queue.add(i, tmp);
            path.removeLast();
        }
    }
}
