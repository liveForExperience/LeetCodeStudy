package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/16 12:03
 */
public class Interview_11_1_旋转数组的最小数字 {
    public int minArray(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }

        if (numbers[0] < numbers[numbers.length - 1]) {
            return numbers[0];
        }

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }

        return numbers[0];
    }
}
