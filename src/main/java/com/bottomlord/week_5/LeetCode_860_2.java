package com.bottomlord.week_5;

public class LeetCode_860_2 {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int num: bills) {
            if (num == 5) {
                five++;
                continue;
            }

            if (num == 10) {
                ten++;

                if (five >= 1) {
                    five--;
                    continue;
                }

                return false;
            }

            if (num == 20) {
                if (ten >= 1 && five >= 1) {
                    ten--;
                    five--;
                    continue;
                }

                if (five >= 3) {
                    five -= 3;
                    continue;
                }

                return false;
            }
        }

        return true;
    }
}