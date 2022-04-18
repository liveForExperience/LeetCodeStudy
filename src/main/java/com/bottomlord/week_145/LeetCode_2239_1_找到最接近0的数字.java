package com.bottomlord.week_145;

/**
 * @author chen yue
 * @date 2022-04-18 20:04:19
 */
public class LeetCode_2239_1_找到最接近0的数字 {
    public int findClosestNumber(int[] nums) {
        int min = Integer.MAX_VALUE, ans = Integer.MIN_VALUE;
        for (int num : nums) {
            int distance = Math.abs(num);
            if (distance < min) {
                min = distance;
                ans = num;
            } else if (distance == min) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }
}
