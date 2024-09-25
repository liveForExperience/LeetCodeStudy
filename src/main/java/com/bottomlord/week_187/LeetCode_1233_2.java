package com.bottomlord.week_187;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chen yue
 * @date 2023-02-08 08:32:20
 */
public class LeetCode_1233_2 {
    public List<String> removeSubfolders(String[] folder) {
        Tire tire = new Tire();
        for (String s : folder) {
            tire.insert(s);
        }
        return tire.find();
    }

    private static class Tire {
        TireNode root;

        public Tire() {
            root = new TireNode(null);
        }

        public void insert(String str) {
            List<String> strs = Arrays.stream(str.split("/")).filter(s -> !Objects.equals(s, "")).collect(Collectors.toList());
            TireNode node = root;
            for (String s : strs) {
                TireNode child = node.children.get(s);
                if (child == null) {
                    child = new TireNode(s);
                    node.children.put(s, child);
                }

                if (node.end) {
                    return;
                }

                node = child;
            }

            node.end = true;
        }

        public List<String> find() {
            List<String> list = new ArrayList<>();
            doFind(root, list, new StringBuilder());
            return list;
        }

        private void doFind(TireNode node, List<String> list, StringBuilder sb) {
            if (node == null) {
                return;
            }

            if (node.end) {
                list.add(sb.toString());
                return;
            }

            Map<String, TireNode> children = node.children;
            for (Map.Entry<String, TireNode> entry : children.entrySet()) {
                int len = sb.length();
                sb.append("/").append(entry.getKey());
                doFind(entry.getValue(), list, sb);
                sb.setLength(len);
            }
        }

        static class TireNode {

            String s;
            Map<String, TireNode> children;
            private boolean end;

            public TireNode(String s) {
                this.s = s;
                this.children = new HashMap<>();
                this.end = false;
            }
        }
    }
}