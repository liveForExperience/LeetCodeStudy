package com.bottomlord.week_107;

import com.bottomlord.TreeNode;

import java.util.*;

public class LeetCode_987_1_二叉树的垂序遍历 {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        List<List<Integer>> ans = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> rowMap : map.values()) {
            List<Integer> subList= new ArrayList<>();
            for (PriorityQueue<Integer> queue : rowMap.values()) {
                while (!queue.isEmpty()) {
                    subList.add(queue.poll());
                }
            }
            ans.add(subList);
        }
        return ans;
    }

    private void dfs(TreeNode node, int row, int col, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (node == null) {
            return;
        }

        TreeMap<Integer, PriorityQueue<Integer>> rowMap = map.getOrDefault(col, new TreeMap<>());
        PriorityQueue<Integer> rowSet = rowMap.getOrDefault(row, new PriorityQueue<>());
        rowSet.add(node.val);
        rowMap.put(row, rowSet);
        map.put(col, rowMap);

        dfs(node.left, row + 1, col - 1, map);
        dfs(node.right, row + 1, col + 1, map);
    }
}
