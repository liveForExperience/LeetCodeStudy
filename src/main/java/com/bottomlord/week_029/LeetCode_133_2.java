package com.bottomlord.week_029;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/1/22 11:12
 */
public class LeetCode_133_2 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (Node neighbour : n.neighbors) {
                if (!map.containsKey(neighbour)) {
                    map.put(neighbour, new Node(neighbour.val, new ArrayList<>()));
                    queue.offer(neighbour);
                }
                map.get(n).neighbors.add(map.get(neighbour));
            }
        }

        return map.get(node);
    }
}