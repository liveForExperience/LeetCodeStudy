package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-19 21:30:44
 */
public class LeetCode_2103_1_环和杆 {
    public int countPoints(String rings) {
        int n = rings.length();
        int[] bucket = new int[10];

        for (int i = 0; i < n; i += 2) {
            char color = rings.charAt(i);
            int index = color == 'R' ? 0 : color == 'G' ? 1 : 2;
            int stick = rings.charAt(i + 1) - '0';

            bucket[stick] |= 1 << index;
        }

        int count = 0;
        for (int num : bucket) {
            if (num == 6) {
                count++;
            }
        }

        return count;
    }
}
