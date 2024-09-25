package com.bottomlord.week_043;

/**
 * @author ChenYue
 * @date 2020/4/29 9:03
 */
public class Interview_1706_2 {
    public int numberOf2sInRange(int n) {
        String str = String.valueOf(n);
        int count = 0, len = str.length();

        for (int i = len - 1; i >= 0; i--) {
            int left = i == 0 ? 0 : Integer.parseInt(str.substring(0, i));
            int cur = Integer.parseInt(Character.toString(str.charAt(i)));
            if (cur > 2) {
                left++;
            }

            int right = (int) Math.pow(10, len - i - 1);
            count += left * right;

            if (cur == 2) {
                right = i + 1 < len ? Integer.parseInt(str.substring(i + 1)) + 1 : 1;
                count += right;
            }
        }

        return count;
    }
}