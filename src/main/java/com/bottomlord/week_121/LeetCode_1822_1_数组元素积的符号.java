package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-01 08:55:17
 */
public class LeetCode_1822_1_数组元素积的符号 {
    public int arraySign(int[] nums) {
        boolean flag = true;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }

            if (num < 0) {
                flag = !flag;
            }
        }

        return flag ? 1 : -1;
    }
}
