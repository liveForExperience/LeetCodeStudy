package com.bottomlord.week_1;

import com.bottomlord.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/13 17:58
 */
public class LeetCode_589_1_N叉树的前序遍历 {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }

    private void dfs(List<Integer> ans, Node node) {
        if (node == null) {
            return;
        }

        ans.add(node.val);

        for (Node child: node.children) {
            dfs(ans, child);
        }
    }
}
