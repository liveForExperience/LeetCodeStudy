package com.bottomlord.week_022;

public class LeetCode_503_1_下一个更大元素II {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < nums.length; i++) {
            int j = (i + 1) % n;
            boolean flag = false;
            while (i != j) {
                if (nums[i] < nums[j]) {
                    flag = true;
                    break;
                }

                j = (j + 1) % n;
            }

            if (flag) {
                ans[i] = nums[j];
            } else {
                ans[i] = -1;
            }
        }

        return ans;
    }
}
