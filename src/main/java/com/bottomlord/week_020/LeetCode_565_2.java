package com.bottomlord.week_020;

public class LeetCode_565_2 {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length + 1];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int count = 0, num = nums[i];
                do {
                    num = nums[num];
                    count++;
                    visited[num] = true;
                } while (num != nums[i]);

                ans = Math.max(ans, count);
            }
        }

        return ans;
    }
}