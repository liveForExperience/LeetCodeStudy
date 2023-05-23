package com.bottomlord;

/**
 * @author chen yue
 * @date 2023-05-23 15:09:07
 */
public class RopeTreeNode {
    public int len;
    public String val;
    public RopeTreeNode left;
    public RopeTreeNode right;

    RopeTreeNode() {
    }

    RopeTreeNode(String val) {
        this.len = 0;
        this.val = val;
    }

    RopeTreeNode(int len) {
        this.len = len;
        this.val = "";
    }

    RopeTreeNode(int len, RopeTreeNode left, RopeTreeNode right) {
        this.len = len;
        this.val = "";
        this.left = left;
        this.right = right;
    }
}
