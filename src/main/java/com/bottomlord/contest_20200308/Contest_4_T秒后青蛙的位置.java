package com.bottomlord.contest_20200308;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/3/8 17:21
 */
public class Contest_4_T秒后青蛙的位置 {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new TreeNode(i));
        }

        Map<TreeNode, List<TreeNode>> abMap = new HashMap<>();
        Map<TreeNode, List<TreeNode>> baMap = new HashMap<>();

        for (int[] edge : edges) {
            List<TreeNode> aList = abMap.getOrDefault(map.get(edge[0]), new ArrayList<>());
            aList.add(map.get(edge[1]));
            abMap.put(map.get(edge[0]), aList);

            List<TreeNode> bList = baMap.getOrDefault(map.get(edge[1]), new ArrayList<>());
            bList.add(map.get(edge[0]));
            baMap.put(map.get(edge[1]), bList);
        }

        dfs(map.get(1), abMap, baMap);

        int time = 0, branch = 1;
        TreeNode node = map.get(target);
        while (node != null && node.val != 1) {
            time++;
            node = node.parent;
            if (node != null) {
                branch *= node.subs.size();
            }
        }

        if (t < time) {
            return 0;
        }

        if (t > time && map.get(target).subs.size() > 0) {
            return 0;
        }

        return 1.0 / branch;
    }

    private void dfs(TreeNode from, Map<TreeNode, List<TreeNode>> abMap, Map<TreeNode, List<TreeNode>> baMap) {
        List<TreeNode> subs = new ArrayList<>();
        if (abMap.containsKey(from)) {
            subs.addAll(abMap.get(from));
        }

        if (baMap.containsKey(from)) {
            subs.addAll(baMap.get(from));
        }

        for (TreeNode sub : subs) {
            if (sub == from.parent) {
                continue;
            }

            sub.parent = from;
            from.subs.add(sub);
            dfs(sub, abMap, baMap);
        }
    }

    class TreeNode {
        public int val;
        public TreeNode parent;
        public List<TreeNode> subs;

        public TreeNode(int val) {
            this.val = val;
            subs = new ArrayList<>();
        }
    }
}
