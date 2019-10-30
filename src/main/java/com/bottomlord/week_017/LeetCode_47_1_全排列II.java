package com.bottomlord.week_017;

import java.util.*;

public class LeetCode_47_1_全排列II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int[] visited = new int[nums.length];
        backTrace(nums, new LinkedList<>(), ans, visited);
        return ans;
    }

    private void backTrace(int[] nums, LinkedList<Integer> list, List<List<Integer>> ans, int[] visited) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] != 1) {
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0) {
                    continue;
                }

                list.addLast(nums[i]);
                visited[i] = 1;
                backTrace(nums, list, ans, visited);
                visited[i] = 0;
                list.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        LeetCode_47_1_全排列II t = new LeetCode_47_1_全排列II();
        t.permuteUnique(new int[]{1,1,2});
    }
}
