package com.bottomlord.week_111;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-08-26 08:39:35
 */
public class LeetCode_881_2 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length, head = 0, tail = n - 1, count = 0;
        while (head < tail) {
            if (people[head] + people[tail] <= limit) {
                head++;
            }
            tail--;
            count++;
        }

        return head == tail ? count + 1 : count;
    }
}
