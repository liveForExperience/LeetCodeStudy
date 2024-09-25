package com.bottomlord.week_103;

import com.bottomlord.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2021/6/30 8:14
 */
public class LeetCode_offer37_1_序列化二叉树 {
    public class Codec {
        private static final String SPLIT_STR = "::";

        public String serialize(TreeNode root) {
            return doSerialize(root);
        }

        public TreeNode deserialize(String data) {
            return doDeserialize(new LinkedList<>(Arrays.asList(data.split(","))));
        }

        private String doSerialize(TreeNode node) {
            if (node == null) {
                return "null";
            }

            return node.val + "," + doSerialize(node.left) + "," + doSerialize(node.right);
        }

        private TreeNode doDeserialize(LinkedList<String> list) {
            if (list == null || list.isEmpty()) {
                return null;
            }

            if (Objects.equals(list.getFirst(), "null")) {
                list.removeFirst();
                return null;
            }

            TreeNode node = new TreeNode(Integer.parseInt(list.getFirst()));
            list.removeFirst();
            node.left = doDeserialize(list);
            node.right = doDeserialize(list);
            return node;
        }
    }
}
