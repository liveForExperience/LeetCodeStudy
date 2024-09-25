package com.bottomlord.week_202;

import com.bottomlord.RopeTreeNode;

/**
 * @author chen yue
 * @date 2023-05-23 15:10:55
 */
public class LeetCode_2689_1_ExtractKthCharacterFromTheRopeTree {
    public char getKthCharacter(RopeTreeNode root, int k) {
        return dfs(root).charAt(k - 1);
    }

    private String dfs(RopeTreeNode node) {
        if (node == null) {
            return "";
        }

        if (node.left == null && node.right == null) {
            return node.val;
        }

        return dfs(node.left) + dfs(node.right);
    }
}
