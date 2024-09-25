package com.bottomlord.week_081;

import com.bottomlord.Node;

import java.util.ArrayList;

/**
 * @author ChenYue
 * @date 2021/1/25 11:22
 */
public class LeetCode_428_2 {
    class Codec {
        public String serialize(Node root) {
            return root != null ? encode(root) : null;
        }

        public Node deserialize(String data) {
            return data != null ? decode(data.split(" "), new Counter()) : null;
        }

        private String encode(Node node) {
            if (node == null) {
                return null;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(node.val).append(" ");
            sb.append(node.children.size()).append(" ");

            for (Node child : node.children) {
                sb.append(encode(child));
            }

            return sb.toString();
        }

        private Node decode(String[] arr, Counter counter) {
            if (counter.get() == arr.length) {
                return null;
            }

            Node root = new Node(Integer.parseInt(arr[counter.add()]), new ArrayList<>());
            int count = Integer.parseInt(arr[counter.add()]);

            for (int i = 0; i < count; i++) {
                Node node = decode(arr, counter);
                root.children.add(node);
            }

            return root;
        }
    }

    static class Counter {
        private int count;

        public int add() {
            return this.count++;
        }

        public int get() {
            return this.count;
        }
    }
}
