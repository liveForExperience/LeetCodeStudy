package com.bottomlord.week_006;

public class LeetCode_504_1_七进制数 {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        boolean isP = num >= 0;
        num = isP ? num : Math.abs(num);

        while (num != 0) {
            int p = num % 7;
            sb.insert(0, p);

            num /= 7;
        }

        if (!isP) {
            sb.insert(0, "-");
        }
        return sb.toString();
    }
}
