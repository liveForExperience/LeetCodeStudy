package com.bottomlord.week_228;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-11-25 19:50:15
 */
public class LeetCode_1457_1_二叉树中的伪回文路径 {
    public int pseudoPalindromicPaths (TreeNode root) {
        return dfs(root, new HashMap<>());
    }

    private int dfs(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        int val = node.val;
        map.put(val, map.getOrDefault(val, 0) + 1);

        int ans;
        if (node.left == null && node.right == null) {
            ans = isFakePalindrome(map) ? 1 : 0;
        } else {
            ans = dfs(node.left, map) + dfs(node.right, map);
        }

        removeVal(map, val);
        return ans;
    }

    private boolean isFakePalindrome(Map<Integer, Integer> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }

        int odd = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getValue();
            if (num % 2 != 0) {
                odd++;
            }

            if (odd > 1) {
                return false;
            }
        }

        return true;
    }

    private void removeVal(Map<Integer, Integer> map, int val) {
        map.put(val, map.get(val) - 1);
        if (map.get(val) == 0) {
            map.remove(val);
        }
    }
}
