package com.bottomlord.week_124;

/**
 * @author chen yue
 * @date 2021-11-24 08:42:43
 */
public class LeetCode_423_1_从英文中重建数字 {
    public String originalDigits(String s) {
        char[] cs = s.toCharArray();
        int[] nums = new int[10], bucket = new int[26];
        for (char c : cs) {
            bucket[c - 'a']++;
        }

        nums[0] = bucket['z' - 'a'];
        nums[2] = bucket['w' - 'a'];
        nums[4] = bucket['u' - 'a'];
        nums[6] = bucket['x' - 'a'];
        nums[8] = bucket['g' - 'a'];
        nums[3] = bucket['h' - 'a'] - nums[8];
        nums[5] = bucket['f' - 'a'] - nums[4];
        nums[7] = bucket['s' - 'a'] - nums[6];
        nums[9] = bucket['i' - 'a'] - nums[5] - nums[6] - nums[8];
        nums[1] = bucket['n' - 'a'] - nums[7] - 2 * nums[9];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < nums[i]; j++) {
                sb.append(i);
            }
        }

        return sb.toString();
    }
}
