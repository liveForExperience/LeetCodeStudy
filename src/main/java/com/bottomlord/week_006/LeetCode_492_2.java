package com.bottomlord.week_006;

public class LeetCode_492_2 {
    public int[] constructRectangle(int area) {
        int num = (int) Math.sqrt(area);

        while (area % num != 0) {
            num--;
        }

        return new int[]{area / num, num};
    }
}