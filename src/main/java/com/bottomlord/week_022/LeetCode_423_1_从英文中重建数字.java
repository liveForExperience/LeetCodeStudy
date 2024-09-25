package com.bottomlord.week_022;

public class LeetCode_423_1_从英文中重建数字 {
    public String originalDigits(String s) {
        char[] cs = new char[26 + (int)'a'];

        for (char c : s.toCharArray()) {
            cs[c]++;
        }

        int[] nums = new int[10];
        nums[0] = cs['z'];
        nums[2] = cs['w'];
        nums[4] = cs['u'];
        nums[6] = cs['x'];
        nums[8] = cs['g'];
        nums[3] = cs['h'] - nums[8];
        nums[5] = cs['f'] - nums[4];
        nums[7] = cs['s'] - nums[6];
        nums[9] = cs['i'] - nums[5] - nums[6] - nums[8];
        nums[1] = cs['n'] - nums[7] - 2 * nums[9];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < nums[i]; j++) {
                sb.append(i);
            }
        }

        return sb.toString();
    }
}
