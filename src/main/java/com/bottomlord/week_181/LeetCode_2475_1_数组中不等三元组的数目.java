package com.bottomlord.week_181;

/**
 * @author chen yue
 * @date 2023-01-01 13:15:02
 */
public class LeetCode_2475_1_数组中不等三元组的数目 {
    public int unequalTriplets(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int n1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int n2 = nums[j];

                if (n1 == n2) {
                    continue;
                }

                for (int k = j + 1; k < nums.length; k++) {
                    int n3 = nums[k];
                    if (n1 == n3 || n2 == n3) {
                        continue;
                    }

                    ans++;
                }
            }
        }

        return ans;
    }
}
