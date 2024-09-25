package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-15 08:54:25
 */
public class LeetCode_2169_1_得到0的操作数 {
    public int countOperations(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }

        return 1 + countOperations(num1 >= num2 ? num1 - num2 : num1, num1 >= num2 ? num2 : num2 - num1);
    }
}
