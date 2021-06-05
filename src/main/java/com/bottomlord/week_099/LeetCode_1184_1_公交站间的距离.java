package com.bottomlord.week_099;

public class LeetCode_1184_1_公交站间的距离 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int index = start, r = 0, l = 0, n = distance.length;
        while (index != destination) {
            r += distance[index];
            index = (index + 1) %  n;
        }

        index = start;
        while (index != destination) {
            l += distance[(index - 1 + n) % n];
            index = (index - 1 + n) % n;
        }

        return Math.min(l, r);
    }
}
