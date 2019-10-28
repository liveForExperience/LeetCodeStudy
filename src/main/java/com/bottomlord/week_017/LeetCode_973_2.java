package com.bottomlord.week_017;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class LeetCode_973_2 {
    public int[][] kClosest(int[][] points, int K) {
        divideAndConquer(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void divideAndConquer(int[][] points, int head, int tail, int k) {
        if (head >= tail) {
            return;
        }

        int oldHead = head, oldTail = tail, headDis = distance(points, head);
        while (head < tail) {
            while (head < tail && distance(points, tail) >= headDis) {
                tail--;
            }
            swap(points, head, tail);

            while (head < tail && distance(points, head) <= headDis) {
                head++;
            }
            swap(points, head, tail);
        }

        if (k <= head - oldHead + 1) {
            divideAndConquer(points, oldHead, head, k);
        } else {
            divideAndConquer(points, head + 1, oldTail, k - (head - oldHead + 1));
        }
    }

    private int distance(int[][] points, int x) {
        return points[x][0] * points[x][0] + points[x][1] * points[x][1];
    }

    private void swap(int[][] points, int x, int y) {
        int[] tmp = points[x];
        points[x] = points[y];
        points[y] = tmp;
    }
}