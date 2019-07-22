package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/22 18:27
 */
public class LeetCode_575_2 {
    public int distributeCandies(int[] candies) {
        int min = candies[0], max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            min = Math.min(min, candies[i]);
            max = Math.max(min, candies[i]);
        }

        boolean[] buckets = new boolean[max - min + 1];
        for (int num : candies) {
            buckets[num - min] = true;
        }

        int count = 0;
        int len = candies.length / 2;
        for (boolean b: buckets) {
            if (b) {
                count++;
            }

            if (count >= len) {
                return count;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LeetCode_575_2 test = new LeetCode_575_2();
        test.distributeCandies(new int[]{1,1,2,3});
    }
}