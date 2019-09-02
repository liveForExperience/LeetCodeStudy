package com.bottomlord.week_001;

import com.bottomlord.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/13 17:11
 */
public class LeetCode_590_1_N叉树的后序遍历 {
    public List<Integer> postorder(Node root) {
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
