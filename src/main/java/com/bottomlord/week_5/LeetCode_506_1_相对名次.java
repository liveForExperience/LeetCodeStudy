package com.bottomlord.week_5;

public class LeetCode_506_1_相对名次 {
    public String[] findRelativeRanks(int[] nums) {
        String[] ans = new String[nums.length];
        int max = 0;
        for (int num: nums) {
            max = Math.max(num, max);
        }

        Integer[] bucket = new Integer[max + 1];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]] = i;
        }

        int count = 0;
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                count++;
                ans[bucket[i]] = count > 3 ? "" + count : count == 1 ? "Gold Medal" : count == 2 ? "Silver Medal" : "Bronze Medal";
            }
        }
        return ans;
    }
}
