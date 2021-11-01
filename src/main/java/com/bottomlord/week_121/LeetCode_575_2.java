package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-01 08:49:27
 */
public class LeetCode_575_2 {
    public int distributeCandies(int[] candyType) {
        boolean[] bucket = new boolean[200001];
        int count = 0;
        for (int num : candyType) {
            if (!bucket[num + 100000]) {
                count++;
                bucket[num + 100000] = true;
            }
        }
        return Math.min(count, candyType.length / 2);
    }
}
