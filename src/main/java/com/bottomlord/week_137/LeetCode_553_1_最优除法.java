package com.bottomlord.week_137;

/**
 * @author chen yue
 * @date 2022-02-27 20:01:45
 */
public class LeetCode_553_1_最优除法 {
    public String optimalDivision(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return String.valueOf(nums[0]);
        }

        if (len == 2) {
            return nums[0] + "/" + nums[1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                sb.append(nums[i]).append("/").append("(");
                continue;
            }

            sb.append(nums[i]);
            if (i != len - 1) {
                sb.append("/");
            } else {
                sb.append(")");
            }
        }

        return sb.toString();
    }
}
