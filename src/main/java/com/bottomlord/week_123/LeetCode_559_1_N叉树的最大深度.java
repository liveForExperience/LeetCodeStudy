package com.bottomlord.week_123;

import java.util.List;

/**
 * @author chen yue
 * @date 2021-11-21 22:31:09
 */
public class LeetCode_559_1_N叉树的最大深度 {
    class Solution {
        public int maxDepth(Node root) {
            if (root == null || root.children == null) {
                return 0;
            }

            int ans = 0;
            for (Node child : root.children) {
                ans = Math.max(ans, maxDepth(child));
            }

            return ans + 1;
        }

        private class Node {
            public int val;
            public List<Node> children;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }
    }
}
