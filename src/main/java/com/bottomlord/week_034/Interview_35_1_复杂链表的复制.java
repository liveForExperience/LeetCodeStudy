package com.bottomlord.week_034;


import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/26 9:08
 */
public class Interview_35_1_复杂链表的复制 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        map.put(null, null);

        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }
}
