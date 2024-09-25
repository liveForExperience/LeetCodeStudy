package com.bottomlord.week_050;

/**
 * @author ChenYue
 * @date 2020/6/15 17:36
 */
public class LeetCode_43_1_字符串相乘 {
    public String multiply(String num1, String num2) {
        String zero = "0";
        if (zero.equals(num1) || zero.equals(num2)) {
            return zero;
        }

        int[] arr = new int[num1.length() + num2.length() - 1];
        int start = arr.length - 1;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0', index = start, carry = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                int c = a * b;
                int remainder = c % 10 + carry;
                carry = c / 10;
                int sum = arr[index] + remainder;
                arr[index] = sum % 10;
                carry += sum / 10;
                index--;
            }

            if (carry > 0) {
                arr[index] += carry;
            }

            start--;
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int num : arr) {
            if (num != 0) {
                break;
            }
            index++;
        }

        for (; index < arr.length; index++) {
            sb.append(arr[index]);
        }
        return sb.toString();
    }
}
