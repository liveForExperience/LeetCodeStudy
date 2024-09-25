package com.bottomlord.week_270;

/**
 * @author chen yue
 * @date 2024-09-16 19:30:51
 */
public class LeetCode_1184_1_公交车站间的距离 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        return Math.min(
                    distance(distance, start, destination),
                    distance(distance, destination, start)
                );
    }

    private int distance(int[] distance, int start, int end) {
        int sum = 0;
        for (int i = start; i != end; i = (i + 1) % distance.length) {
            sum += distance[i];
        }
        return sum;
    }
}
