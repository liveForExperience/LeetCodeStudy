package com.bottomlord.week_019;

public class LeetCode_553_1_最优除法 {
    public String optimalDivision(int[] nums) {
        R[][] memo = new R[nums.length + 1][nums.length + 1];
        R r = recurse(nums, 0, nums.length - 1, memo);
        return r.maxStr;
    }

    private R recurse(int[] nums, int start, int end, R[][] memo) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        if (start == end) {
            R r = new R();
            r.max = nums[start];
            r.min = nums[start];
            r.maxStr = "" + nums[start];
            r.minStr = "" + nums[start];
            memo[start][end] = r;
            return r;
        }

        R r = new R();
        r.max = Double.MIN_VALUE;
        r.min = Double.MAX_VALUE;
        r.maxStr = r.minStr = "";

        for (int i = start; i < end; i++) {
            R left = recurse(nums, start, i, memo);
            R right = recurse(nums, i + 1, end, memo);

            if (r.max < (left.max / right.min)) {
                r.max = left.max / right.min;
                r.maxStr = left.maxStr + "/" + (i + 1 != end ? "(" : "") + right.minStr + (i + 1 != end ? ")" : "");
            }

            if (r.min > (left.min / right.max)) {
                r.min = left.min / right.max;
                r.minStr = left.minStr + "/" + (i + 1 != end ? "(" : "") + right.maxStr + (i + 1 != end ? ")" : "");
            }
        }

        memo[start][end] = r;
        return r;
    }

    private class R {
        private double max;
        private double min;
        private String maxStr;
        private String minStr;
    }
}
