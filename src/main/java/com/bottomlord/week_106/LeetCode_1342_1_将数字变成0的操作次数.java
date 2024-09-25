package com.bottomlord.week_106;

/**
 * @author ChenYue
 * @date 2021/7/19 8:51
 */
public class LeetCode_1342_1_将数字变成0的操作次数 {
    public int numberOfSteps(int num) {
        int count = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num--;
            }

            count++;
        }
        return count;
    }
}
