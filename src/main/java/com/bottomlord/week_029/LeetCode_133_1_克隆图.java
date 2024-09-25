package com.bottomlord.week_029;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/1/22 10:30
 */
public class LeetCode_133_1_克隆图 {
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);
        for (Node neighbour : node.neighbors) {
            clone.neighbors.add(dfs(neighbour, map));
        }

        return clone;
    }
}
