package com.bottomlord.week_040;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/4/7 8:25
 */
public class Interview_1010_2 {
    class StreamRank {
        private Node root;
        public StreamRank() {
        }

        public void track(int x) {
            if (root == null) {
                this.root = new Node(x);
            } else {
                Node node = this.root;
                while (true) {
                    if (node.val == x) {
                        node.count++;
                        break;
                    } else if (node.val > x) {
                        node.count++;
                        if (node.left != null) {
                            node = node.left;
                        } else {
                            node.left = new Node(x);
                            break;
                        }
                    } else {
                        if (node.right != null) {
                            node = node.right;
                        } else {
                            node.right = new Node(x);
                            break;
                        }
                    }
                }
            }
        }

        public int getRankOfNumber(int x) {
            int ans = 0;
            Node node = root;
            while (node != null) {
                if (node.val == x) {
                    ans += node.count;
                    break;
                } else if (node.val > x) {
                    node = node.left;
                } else {
                    ans += node.count;
                    node = node.right;
                }
            }
            return ans;
        }
    }

    class Node {
        public int val;
        public int count;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val = val;
            this.count = 1;
        }
    }
}
