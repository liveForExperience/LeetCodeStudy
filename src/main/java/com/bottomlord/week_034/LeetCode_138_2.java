package com.bottomlord.week_034;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/27 8:01
 */
public class LeetCode_138_2 {
    public Node copyRandomList(Node head) {
        return dfs(head, new HashMap<>());
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node copy = new Node(node.val);
        map.put(node, copy);

        copy.next = dfs(node.next, map);
        copy.random = dfs(node.random, map);

        return copy;
    }
}