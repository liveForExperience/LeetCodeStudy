package com.bottomlord.week_163;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-08-22 18:53:12
 */
public class LeetCode_655_1_输出二叉树 {
    public List<List<String>> printTree(TreeNode root) {
        int m = depth(root), height = m - 1,  n = (1 << m) - 1;
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add("");
            }
            ans.add(list);
        }

        print(root, ans, 0, (n - 1) / 2, height);
        return ans;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    private void print(TreeNode node, List<List<String>> ans, int r, int c, int height) {
        if (node == null) {
            return;
        }

        ans.get(r).set(c, "" + node.val);
        print(node.left, ans, r + 1, c - (1 << (height - r - 1)), height);
        print(node.right, ans, r + 1, c + (1 << (height - r - 1)), height);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1), t2 = new TreeNode(2);
        t1.left = t2;

        LeetCode_655_1_输出二叉树 t = new LeetCode_655_1_输出二叉树();
        t.printTree(t1);
    }
}
