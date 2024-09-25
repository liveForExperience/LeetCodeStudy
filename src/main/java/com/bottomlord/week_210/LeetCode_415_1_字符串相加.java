package com.bottomlord.week_210;

/**
 * @author chen yue
 * @date 2023-07-17 09:48:03
 */
public class LeetCode_415_1_字符串相加 {
    public String addStrings(String num1, String num2) {
        char[] cs1 = num1.toCharArray(), cs2 = num2.toCharArray();
        int i1 = cs1.length - 1, i2 = cs2.length - 1, carry = 0;
        StringBuilder ans = new StringBuilder();
        while (i1 >= 0 || i2 >= 0 || carry != 0) {
            int a = i1 >= 0 ? cs1[i1--] - '0' : 0,
                b = i2 >= 0 ? cs2[i2--] - '0' : 0;
            int num = a + b + carry;
            ans.insert(0, num % 10);
            carry = num / 10;
        }
        return ans.toString();
    }
}
