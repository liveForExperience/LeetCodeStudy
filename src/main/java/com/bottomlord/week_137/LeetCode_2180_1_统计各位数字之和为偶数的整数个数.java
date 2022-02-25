package com.bottomlord.week_137;

/**
 * @author chen yue
 * @date 2022-02-25 09:21:35
 */
public class LeetCode_2180_1_统计各位数字之和为偶数的整数个数 {
    public int countEven(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (check(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean check(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum % 2 == 0;
    }
}
