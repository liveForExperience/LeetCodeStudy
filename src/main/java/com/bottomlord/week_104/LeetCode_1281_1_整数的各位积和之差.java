package com.bottomlord.week_104;

/**
 * @author ChenYue
 * @date 2021/7/9 8:32
 */
public class LeetCode_1281_1_整数的各位积和之差 {
    public int subtractProductAndSum(int n) {
        int amass = 1, sum = 0;
        while (n > 0) {
            int digit = n % 10;
            amass *= digit;
            sum += digit;
            n /= 10;
        }

        return amass - sum;
    }
}
