package com.bottomlord.week_217;

/**
 * @author chen yue
 * @date 2023-09-10 13:51:47
 */
public class LeetCode_2594_1_修车的最少时间 {
    private int cars;
    private int[] ranks, rankMap;

    public long repairCars(int[] ranks, int cars) {
        this.cars = cars;
        this.ranks = ranks;
        this.rankMap = new int[101];

        for (int rank : ranks) {
            rankMap[rank]++;
        }

        long head = 0, tail = Long.MAX_VALUE;
        while (head < tail) {
            long m = head + (tail - head) / 2;
            if (check(m)) {
                tail = m;
            } else {
                head = m + 1;
            }
        }

        return head;
    }

    private boolean check(long m) {
        long cnt = 0;
        for (int rank : ranks) {
            cnt += (long)Math.sqrt(m / rank * 1L);

            if (cnt >= cars) {
                return true;
            }
        }

        return false;
    }
}
