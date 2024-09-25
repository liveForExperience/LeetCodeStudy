package com.bottomlord.week_020;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_655_1_输出二叉树 {
    public List<List<String>> printTree(TreeNode root) {
        int depth = getDepth(root), len = 0, count = depth;

        while (count-- > 0) {
            len = len * 2 + 1;
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            List<String> list = new ArrayList<>(len);
            for (int j = 0; j < len; j++) {
                list.add("");
            }
            ans.add(list);
        }

        dfs(root, 0, len, 0, ans);

        return ans;
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }

    private void dfs(TreeNode node, int start, int end, int depth, List<List<String>> ans) {
        if (node == null || start > end) {
            return;
        }

        int mid = start + (end - start) / 2;
        ans.get(depth).set(mid, Integer.toString(node.val));

        dfs(node.left, start, mid - 1, depth + 1, ans);
        dfs(node.right, mid + 1, end, depth + 1, ans);
    }
}
