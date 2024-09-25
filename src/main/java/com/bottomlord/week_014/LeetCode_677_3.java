package com.bottomlord.week_014;

import com.bottomlord.DictNode;

public class LeetCode_677_3 {
    class MapSum {
        private DictNode root;
        public MapSum() {
            this.root = new DictNode(' ');
        }

        public void insert(String key, int val) {
            doInsert(key, root, 0, val, sum(key));
        }

        public int sum(String prefix) {
            return doSum(prefix, root, 0);
        }

        private void doInsert(String key, DictNode node, int index, int val, int oldV) {
            if (index >= key.length()) {
                return;
            }

            for (DictNode child : node.children) {
                if (key.charAt(index) == child.c) {
                    child.sum += val;
                    doInsert(key, child, index + 1, val, oldV);
                    if (index == key.length() - 1 && node.children.isEmpty()) {
                        DictNode tmp = child;
                        while (tmp != null) {
                            tmp.sum -= oldV;
                            tmp = tmp.parent;
                        }
                        return;
                    }
                    return;
                }
            }

            DictNode cur = new DictNode(key.charAt(index));
            cur.sum = val;
            cur.parent = node;
            node.children.add(cur);
            doInsert(key, cur, index + 1, val, oldV);
        }

        private int doSum(String prefix, DictNode node, int index) {
            if (index >= prefix.length()) {
                return 0;
            }

            for (DictNode child : node.children) {
                if (child.c == prefix.charAt(index)) {
                    if (index == prefix.length() - 1) {
                        return child.sum;
                    }

                    return doSum(prefix, child, index + 1);
                }
            }

            return 0;
        }
    }
}