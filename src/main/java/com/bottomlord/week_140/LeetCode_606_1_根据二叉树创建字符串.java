package com.bottomlord.week_140;

import com.bottomlord.TreeNode;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2022-03-19 11:14:40
 */
public class LeetCode_606_1_根据二叉树创建字符串 {
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        String left = tree2str(root.left);
        if (!Objects.equals(left, "")) {
            sb.append("(").append(left).append(")");
        }

        String right = tree2str(root.right);
        if (!Objects.equals(right, "")) {
            if (Objects.equals(left, "")) {
                sb.append("(").append(")");
            }
            sb.append("(").append(right).append(")");
        }

        return sb.toString();
    }
}
