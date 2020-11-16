package com.bottomlord.week_071;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/11/16 9:57
 */
public class LeetCode_LCP08_1_剧情触发时间 {
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] ans = new int[requirements.length];
        Arrays.fill(ans, -1);

        int[][] requirementsCopy = new int[requirements.length][4];
        for (int i = 0; i < requirements.length; i++) {
            requirementsCopy[i][0] = requirements[i][0];
            requirementsCopy[i][1] = requirements[i][1];
            requirementsCopy[i][2] = requirements[i][2];
            requirementsCopy[i][3] = i;
        }

        Arrays.sort(requirementsCopy, Comparator.comparingInt(x -> x[0]));

        int[] sum = new int[]{0, 0, 0};
        LinkedList<int[]> queue = new LinkedList<>();
        Stack<int[]> stack = new Stack<>();
        for (int[] arr : requirementsCopy) {
            queue.offer(arr);
        }

        while (!queue.isEmpty() && queue.getFirst()[0] <= sum[0]) {
            int[] arr = queue.pollFirst();
            if (arr[0] <= sum[0] && arr[1] <= sum[1] && arr[2] <= sum[2]) {
                ans[arr[3]] = 0;
            } else {
                stack.push(arr);
            }
        }

        while (!stack.isEmpty()) {
            queue.offerFirst(stack.pop());
        }

        for (int i = 0; i < increase.length; i++) {
            sum[0] += increase[i][0];
            sum[1] += increase[i][1];
            sum[2] += increase[i][2];

            while (!queue.isEmpty() && queue.getFirst()[0] <= sum[0]) {
                int[] arr = queue.pollFirst();
                if (arr[0] <= sum[0] && arr[1] <= sum[1] && arr[2] <= sum[2]) {
                    ans[arr[3]] = i;
                } else {
                    stack.push(arr);
                }
            }

            while (!stack.isEmpty()) {
                queue.offerFirst(stack.pop());
            }
        }

        return ans;
    }
}
