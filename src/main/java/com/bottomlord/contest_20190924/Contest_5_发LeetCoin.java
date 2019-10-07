package com.bottomlord.contest_20190924;

import com.bottomlord.Node;

import java.util.*;

public class Contest_5_发LeetCoin {
    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i + 1, new Node(i + 1));
        }

        for (int[] arr : leadership) {
            Node node = map.get(arr[1]);
            Node parent = map.get(arr[0]);

            node.parent = parent;
            if (parent.children == null) {
                parent.children = new ArrayList<>();
            }

            parent.children.add(node);
        }

        List<Integer> list = new ArrayList<>();
        for (int[] operation : operations) {
            Node node = map.get(operation[1]);
            if (operation[0] == 1) {
                node.sum += operation[2];
                while (node.parent != null) {
                    node.parent.sum += operation[2];
                    node = node.parent;
                }
            } else if (operation[0] == 2) {
                int sum = operate(node, operation[2]);
                while (node.parent != null) {
                    node.parent.sum += sum;
                    node = node.parent;
                }
            } else {
                list.add((node.sum % 1000000007));
            }
        }

        int index = 0;
        int[] ans = new int[list.size()];
        for (int num : list) {
            ans[index++] = num;
        }
        return ans;
    }

    private int operate(Node node, int num) {
        if (node == null) {
            return 0;
        }

        int sum = num;

        if (node.children != null) {
            for (Node child : node.children) {
                sum += operate(child, num);
            }
        }

        node.sum = sum;
        return sum;
    }
}