package com.bottomlord.week_003;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/27 10:31
 */
public class LeetCode_257_1_二叉树的所有路径 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        rescurse(root, "", ans);
        return ans;
    }

    private void rescurse(TreeNode node, String str, List<String> ans) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            str += node.val;
            ans.add(str);
        }

        rescurse(node.left, str + node.val + "->", ans);
        rescurse(node.right, str + node.val + "->", ans);
    }
}
