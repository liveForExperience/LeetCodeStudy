package com.bottomlord.week_090;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/3/31 8:22
 */
public class LeetCode_90_1_子集II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> memo = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            backTrack(nums, i, ans, new LinkedList<>(), memo, new StringBuilder());
        }

        return ans;
    }

    private void backTrack(int[] nums, int index, List<List<Integer>> ans, LinkedList<Integer> list, Set<String> memo, StringBuilder sb) {
        if (!memo.contains(sb.toString())) {
            memo.add(sb.toString());
            ans.add(new ArrayList<>(list));
        }

        if (index >= nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            int len = sb.length();
            sb.append("#").append(nums[i]);
            if (memo.contains(sb.toString())) {
                sb.setLength(len);
                continue;
            }

            list.add(nums[i]);
            backTrack(nums, i + 1, ans, list, memo, sb);
            list.removeLast();
            sb.setLength(len);
        }
    }
}
