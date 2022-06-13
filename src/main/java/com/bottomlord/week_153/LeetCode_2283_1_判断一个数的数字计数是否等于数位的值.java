package com.bottomlord.week_153;

/**
 * @author chen yue
 * @date 2022-06-13 11:18:27
 */
public class LeetCode_2283_1_判断一个数的数字计数是否等于数位的值 {
    public boolean digitCount(String num) {
        int[] nums = new int[10], targets = new int[10];
        char[] cs = num.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            targets[i] = cs[i] - '0';
            nums[cs[i] - '0']++;
        }

        for (int i = 0; i < cs.length; i++) {
            if (targets[i] != nums[i]) {
                return false;
            }
        }

        return true;
    }
}
