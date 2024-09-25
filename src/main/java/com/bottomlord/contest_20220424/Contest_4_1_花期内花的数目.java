package com.bottomlord.contest_20220424;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-04-24 10:17:38
 */
public class Contest_4_1_花期内花的数目 {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] flower : flowers) {
            int start = flower[0], end = flower[1] + 1;
            if (!map.containsKey(start)) {
                map.put(start, 1);
                queue.offer(start);
            } else {
                map.put(start, map.get(start) + 1);
            }

            if (!map.containsKey(end)) {
                map.put(end, -1);
                queue.offer(end);
            } else {
                map.put(end, map.get(end) - 1);
            }
        }

        int[][] newPersons = new int[persons.length][2];
        for (int i = 0; i < persons.length; i++) {
            newPersons[i] = new int[]{persons[i], i};
        }

        Arrays.sort(newPersons, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int sum = 0;

        int[] ans = new int[persons.length];

        for (int[] newPerson : newPersons) {
            int person = newPerson[0];
            while (!queue.isEmpty() && queue.peek() <= person) {
                sum += map.getOrDefault(queue.poll(), 0);
            }

            ans[newPerson[1]] = sum;
        }

        return ans;
    }
}
