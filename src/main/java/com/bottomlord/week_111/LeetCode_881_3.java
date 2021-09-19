package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-26 09:00:09
 */
public class LeetCode_881_3 {
    public int numRescueBoats(int[] people, int limit) {
        int[] bucket = new int[limit + 1];
        int head = 1, tail = limit, count = 0;

        for (int weight : people) {
            bucket[weight]++;
        }

        while (head < tail) {
            while (head < tail && bucket[head] == 0) {
                head++;
            }

            while (head < tail && bucket[tail] == 0) {
                tail--;
            }

            if (head + tail <= limit) {
                bucket[head]--;
            }

            bucket[tail]--;
            count++;
        }

        if (head == tail) {
            if (2 * head <= limit) {
                count += bucket[head] % 2 == 0 ? bucket[head] / 2 : (bucket[head] + 1) / 2;
            } else {
                count += bucket[head];
            }
        } else {
            count--;
        }

        return count;
    }
}
