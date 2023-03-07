package com.bottomlord.week_191;

/**
 * @author chen yue
 * @date 2023-03-07 19:52:30
 */
public class LeetCode_2578_1_最小和分割 {
    public int splitNum(int num) {
        int[] arr = new int[10];
        while (num > 0) {
            arr[num % 10]++;
            num /= 10;
        }

        int a = 0, b = 0;
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (flag) {
                    a = a * 10 + i;
                } else {
                    b = b * 10 + i;
                }

                flag = !flag;
            }
        }

        return a + b;
    }
}
