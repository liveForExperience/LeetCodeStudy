package com.bottomlord.week_050;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/6/18 13:03
 */
public class LeetCode_1028_2 {
    private int index;
    public TreeNode recoverFromPreorder(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }

        return dfs(S, 0);
    }

    private TreeNode dfs(String s, int level) {
        if (index >= s.length()) {
            return null;
        }

        int i = index, count = 0;

        while (i < s.length() && s.charAt(i) == '-') {
            count++;
            i++;
        }

        if (count != level) {
            return null;
        }

        index = i;
        int val = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            val = val * 10 + (s.charAt(index) - '0');
            index++;
        }

        TreeNode root = new TreeNode(val);
        root.left = dfs(s, level + 1);
        root.right = dfs(s, level + 1);

        return root;
    }
}