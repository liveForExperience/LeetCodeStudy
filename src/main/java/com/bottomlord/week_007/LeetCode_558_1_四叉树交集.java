package com.bottomlord.week_007;

public class LeetCode_558_1_四叉树交集 {
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf) {
            if (quadTree1.val) {
                return quadTree1;
            } else {
                return quadTree2;
            }
        }

        if (quadTree2.isLeaf) {
            if (quadTree2.val) {
                return quadTree2;
            } else {
                return quadTree1;
            }
        }

        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        if (topLeft.isLeaf && topLeft.val && topRight.isLeaf && topRight.val && bottomLeft.isLeaf && bottomLeft.val && bottomRight.isLeaf && bottomRight.val) {
            return new Node(true, true, null, null, null, null);
        }

        if (topLeft.isLeaf && !topLeft.val && topRight.isLeaf && !topRight.val && bottomLeft.isLeaf && !bottomLeft.val && bottomRight.isLeaf && !bottomRight.val) {
            return new Node(false, true, null, null, null, null);
        }

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
