package com.bottomlord.week_119;

/**
 * @author chen yue
 * @date 2021-10-23 14:41:02
 */
public class LeetCode_492_2 {
    public int[] constructRectangle(int area) {
        int num = (int) Math.sqrt(area);

        while (area % num != 0) {
            num--;
        }

        return new int[]{area / num, num};
    }
}
