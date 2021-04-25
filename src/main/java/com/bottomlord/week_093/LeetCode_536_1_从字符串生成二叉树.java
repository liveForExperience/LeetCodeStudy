package com.bottomlord.week_093;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/4/25 13:51
 */
public class LeetCode_536_1_从字符串生成二叉树 {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        String[] elements = getStrElements(s);
        TreeNode node = new TreeNode(Integer.parseInt(elements[0]));
        String[] subTreeStrs = getSubTreeStr(elements[1]);

        if (subTreeStrs != null) {
            String leftStr = subTreeStrs[0];
            if (leftStr != null && leftStr.length() != 0) {
                node.left = str2tree(leftStr.substring(leftStr.indexOf("(") + 1, leftStr.lastIndexOf(")")));
            }

            String rightStr = subTreeStrs[1];
            if (rightStr != null && rightStr.length() != 0) {
                node.right = str2tree(rightStr.substring(rightStr.indexOf("(") + 1, rightStr.lastIndexOf(")")));
            }
        }

        return node;
    }

    private static String[] getStrElements(String s) {
        int index = 0;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == ')') {
                break;
            }

            index++;
        }

        return new String[]{s.substring(0, index), s.substring(index)};
    }

    private String[] getSubTreeStr(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int lNum = 0, rNum = 0, index = 0;
        do {
            while (s.charAt(index) != '(' && s.charAt(index) != ')') {
                index++;
            }

            if (s.charAt(index++) == '(') {
                lNum++;
            } else {
                rNum++;
            }
        } while (lNum != rNum);

        return new String[]{s.substring(0, index), s.substring(index)};
    }
}