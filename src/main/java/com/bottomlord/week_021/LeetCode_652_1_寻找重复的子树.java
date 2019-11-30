package com.bottomlord.week_021;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_652_1_寻找重复的子树 {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, new HashMap<>(), ans);
        return ans;
    }

    private String dfs(TreeNode node, Map<String, Integer> map, List<TreeNode> ans) {
        if (node == null) {
            return "";
        }

        String key = node.val + "," + dfs(node.left, map, ans) + "," + dfs(node.right, map, ans);

        if (map.containsKey(key) && map.get(key) == 1) {
            ans.add(node);
        }

        map.put(key, map.getOrDefault(key, 0) + 1);
        return key;
    }
}
