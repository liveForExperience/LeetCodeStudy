package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/5 22:01
 */
public class Interview_1605_1_阶乘尾数 {
    public int trailingZeroes(int n) {
        return n < 5 ? 0 : trailingZeroes(n / 5) + n / 5;
    }
}
