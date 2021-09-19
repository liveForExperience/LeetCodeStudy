package com.bottomlord.week_111;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-25 08:24:04
 */
public class LeetCode_797_1_所有可能的路径 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, graph, new LinkedList<Integer>(){{this.add(0);}}, ans);
        return ans;
    }

    private void backTrack(int index, int[][] graph, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (index == graph.length - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }

        int[] arr = graph[index];
        for (Integer num : arr) {
            list.addLast(num);
            backTrack(num, graph, list, ans);
            list.removeLast();
        }
    }
}
