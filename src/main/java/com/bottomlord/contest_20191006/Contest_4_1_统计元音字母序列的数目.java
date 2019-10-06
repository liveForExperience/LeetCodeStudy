package com.bottomlord.contest_20191006;

import com.bottomlord.Node;

import java.util.ArrayList;
import java.util.List;

public class Contest_4_1_统计元音字母序列的数目 {
    private char[] a = new char[]{'a', 'e', 'i', 'o', 'u'};
    private int count = 0;

    public int countVowelPermutation(int n) {
        if (n == 0) {
            return 0;
        }

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Node node = new Node(a[i]);
            nodes.add(node);
            if (n == 1) {
                count++;
            } else {
                create(node, 1, n);
            }
        }
        return count;
    }

    private void create(Node node, int level, int n) {
        if (level == n) {
            return;
        }

        if (node.val == 'a') {
            create(new Node('e'), level + 1, n);
            if (level + 1 == n) {
                count++;
            }
        }

        if (node.val == 'e') {
            create(new Node('a'), level + 1, n);
            create(new Node('i'), level + 1, n);
            if (level + 1 == n) {
                count += 2;
            }
        }

        if (node.val == 'i') {
            create(new Node('a'), level + 1, n);
            create(new Node('e'), level + 1, n);
            create(new Node('o'), level + 1, n);
            create(new Node('u'), level + 1, n);

            if (level + 1 == n) {
                count += 4;
            }
        }

        if (node.val == 'o') {
            create(new Node('i'), level + 1, n);
            create(new Node('u'), level + 1, n);
            if (level + 1 == n) {
                count += 2;
            }
        }

        if (node.val == 'u') {
            create(new Node('a'), level + 1, n);
            if (level + 1 == n) {
                count++;
            }
        }
    }
}
