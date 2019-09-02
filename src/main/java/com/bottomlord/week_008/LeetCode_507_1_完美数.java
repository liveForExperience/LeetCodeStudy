package com.bottomlord.week_008;

public class LeetCode_507_1_完美数 {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }

        int sum = 1, i = 2;
        double sqrt = Math.sqrt(num);
        for (; i < sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }

        if (num % sqrt == 0) {
            sum += sqrt;
        }

        return sum == num;
    }
}
