package com.bottomlord.week_134;

/**
 * @author chen yue
 * @date 2022-01-31 20:48:54
 */
public class LeetCode_2154_1_将找到的值乘以2 {
    public int findFinalValue(int[] nums, int original) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int num : nums) {
                if (num == original) {
                    original = original * 2;
                    flag = true;
                    break;
                }
            }
        }

        return original;
    }
}
