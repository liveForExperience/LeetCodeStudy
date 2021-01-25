package com.bottomlord.week_081;

import com.bottomlord.Node;

import java.util.ArrayList;

/**
 * @author ChenYue
 * @date 2021/1/25 8:34
 */
public class LeetCode_428_1_序列化和反序列化N叉树 {
    class Codec {
        private int i = 0;
        public String serialize(Node root) {
            return root != null ? encode(root) : null;
        }

        public Node deserialize(String data) {
            return data != null ? decode(data.split(" ")) : null;
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

        private Node decode(String[] arr) {
            if (i == arr.length) {
                return null;
            }

            Node root = new Node(Integer.parseInt(arr[i++]), new ArrayList<>());
            int childrenCount = Integer.parseInt(arr[i++]);

            for (int i = 0; i < childrenCount; i++) {
                Node node = decode(arr);
                root.children.add(node);
            }

            return root;
        }
    }
}
