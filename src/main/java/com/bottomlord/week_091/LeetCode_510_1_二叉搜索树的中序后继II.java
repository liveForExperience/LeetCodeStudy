package com.bottomlord.week_091;

/**
 * @author ChenYue
 * @date 2021/4/7 12:46
 */
public class LeetCode_510_1_二叉搜索树的中序后继II {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return findLeftChild(node.right);
        } else {
            return findLeftFather(node.parent, node);
        }
    }

    private Node findLeftChild(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return findLeftChild(node.left);
    }

    private Node findLeftFather(Node father, Node child) {
        if (father == null) {
            return null;
        }

        if (father.left == child) {
            return father;
        }

        return findLeftFather(father.parent, father);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
