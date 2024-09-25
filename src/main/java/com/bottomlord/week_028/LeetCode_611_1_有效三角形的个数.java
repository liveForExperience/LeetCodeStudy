package com.bottomlord.week_028;

/**
 * @author ThinkPad
 * @date 2020/1/18 21:40
 */
public class LeetCode_611_1_有效三角形的个数 {
    public int triangleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int a = nums[i], b = nums[j], c = nums[k];
                    if (a + b > c && a + c > b && b + c > a) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
