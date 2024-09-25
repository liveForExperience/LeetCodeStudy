package com.bottomlord.week_050;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2020/6/18 8:21
 */
public class LeetCode_1028_1_从先序遍历还原二叉树 {
    public TreeNode recoverFromPreorder(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }

        return dfs(S, 1);
    }

    private TreeNode dfs(String s, int level) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int firstNumEndIndex = findNumEndIndex(s);
        String rootStr = s.substring(0, firstNumEndIndex);

        TreeNode root = new TreeNode(Integer.parseInt(rootStr));

        if (s.length() == firstNumEndIndex) {
            return root;
        }

        int mid = findMid(s, level);
        if (mid != -1) {
            root.left = dfs(s.substring(firstNumEndIndex + level, mid - level + 1), level + 1);
            root.right = dfs(s.substring(mid + 1), level + 1);
        } else {
            root.left = dfs(s.substring(firstNumEndIndex + level), level + 1);
        }

        return root;
    }

    private int findNumEndIndex(String s) {
        int index = 0;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                break;
            }
            index++;
        }
        return index;
    }

    private int findMid(String s, int level) {
        int mid = -1, count = 0, time = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '-') {
                count = 0;
            } else {
                count++;
            }

            if (i < s.length() - 1 && count == level && s.charAt(i + 1) != '-') {
                time++;

                if (time == 2) {
                    mid = i;
                }
            }
        }

        return mid;
    }
}
