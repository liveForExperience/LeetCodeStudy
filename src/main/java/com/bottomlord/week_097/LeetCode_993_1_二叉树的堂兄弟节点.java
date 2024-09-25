package com.bottomlord.week_097;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/5/17 8:23
 */
public class LeetCode_993_1_二叉树的堂兄弟节点 {
    public boolean isCousins(TreeNode root, int x, int y) {
        Note noteX = new Note(), noteY = new Note();
        dfs(null, root, x, 0, noteX);
        dfs(null, root, y, 0, noteY);
        return noteX.parent != noteY.parent && noteX.level == noteY.level;
    }

    private void dfs(TreeNode parent, TreeNode node, int target, int level, Note note) {
        if (node == null) {
            return;
        }

        if (node.val == target) {
            note.level = level;
            note.parent = parent;
            return;
        }

        dfs(node, node.left, target, level + 1, note);
        dfs(node, node.right, target, level + 1, note);
    }

    class Note {
        private TreeNode parent;
        private int level;
    }
}
