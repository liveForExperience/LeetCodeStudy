package com.bottomlord.week_186;

/**
 * @author chen yue
 * @date 2023-01-31 20:21:17
 */
public class LeetCode_2544_1_交替数字和 {
    public int alternateDigitSum(int n) {
        int len = 0, num = n;
        while (num > 0) {
            len++;
            num /= 10;
        }

        int[] arr = new int[n];
        int index = 0;
        while (n > 0) {
            arr[index++] = n % 10;
            n /= 10;
        }

        boolean flag = true;
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum = flag ? sum + arr[i] : sum - arr[i];
            flag = !flag;
        }

        return sum;
    }
}
