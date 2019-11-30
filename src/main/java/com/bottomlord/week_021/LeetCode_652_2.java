package com.bottomlord.week_021;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_652_2 {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, new HashMap<>(), ans);
        return ans;
    }

    private int dfs(TreeNode node, Map<Integer, Integer> map, List<TreeNode> ans) {
        if (node == null) {
            return "#".hashCode();
        }

        String key = node.val + "," + dfs(node.left, map, ans) + "," + dfs(node.right, map, ans);

        int uid = key.hashCode();
        map.put(uid, map.getOrDefault(uid, 0) + 1);

        if (map.get(uid) == 2) {
            ans.add(node);
        }

        return uid;
    }
}
