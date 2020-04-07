package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/7 13:26
 */
public class Interview_1601_1_交换数字 {
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }
}
