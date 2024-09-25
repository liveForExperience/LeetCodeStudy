package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_894_1_所有可能的满二叉树 {
    public List<TreeNode> allPossibleFBT(int N) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        recurse(map, N);
        return map.get(N);
    }

    private List<TreeNode> recurse(Map<Integer, List<TreeNode>> map, int n) {
        if (!map.containsKey(n)) {
            List<TreeNode> nodes = new ArrayList<>();
            if (n == 1) {
                nodes.add(new TreeNode(0));
            } else if (n % 2 == 1){
                for (int left = 1; left < n; left++) {
                    int right = n - 1 - left;
                    for (TreeNode leftNode : recurse(map, left)) {
                        for (TreeNode rightNode : recurse(map, right)) {
                            TreeNode root = new TreeNode(0);
                            root.left = leftNode;
                            root.right = rightNode;
                            nodes.add(root);
                        }
                    }
                }
            }
            map.put(n, nodes);
        }

        return map.get(n);
    }
}
