package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_894_2 {
    Map<Integer, List<TreeNode>> map = new HashMap<>();
    public List<TreeNode> allPossibleFBT(int N) {
        if (!map.containsKey(N)) {
            List<TreeNode> nodes = new ArrayList<>();
            if (N == 1) {
                nodes.add(new TreeNode(0));
            } else if (N % 2 == 1) {
                for (int i = 1; i < N; i++) {
                    int j = N - 1 - i;
                    for (TreeNode left : allPossibleFBT(i)) {
                        for (TreeNode right : allPossibleFBT(j)) {
                            TreeNode root = new TreeNode(0);
                            root.left = left;
                            root.right = right;
                            nodes.add(root);
                        }
                    }
                }
            }

            map.put(N, nodes);
        }

        return map.get(N);
    }
}
