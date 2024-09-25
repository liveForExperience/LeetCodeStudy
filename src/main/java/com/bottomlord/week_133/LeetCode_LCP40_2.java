package com.bottomlord.week_133;

import java.util.LinkedList;

/**
 * @author chen yue
 * @date 2022-01-30 16:00:18
 */
public class LeetCode_LCP40_2 {
    public int maxmiumScore(int[] cards, int cnt) {
        int[] bucket = new int[1001];
        LinkedList<Integer> oddList = new LinkedList<>(), evenList = new LinkedList<>();
        for (int card : cards) {
            bucket[card]++;
        }

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == 0) {
                continue;
            }

            if (i % 2 == 0) {
                evenList.add(i);
            } else {
                oddList.add(i);
            }
        }

        int sum = 0, minOdd = -1, minEven = -1;
        for (int i = bucket.length - 1; i >= 0; i--) {
            int num = bucket[i];
            if (num == 0) {
                continue;
            }

            int count = Math.min(cnt, num);
            sum += count * i;

            bucket[i] -= count;
            if (i % 2 == 0) {
                minEven = i;
                if (bucket[i] == 0) {
                    evenList.removeFirst();
                }
            } else {
                minOdd = i;
                if (bucket[i] == 0) {
                    oddList.removeFirst();
                }
            }

            cnt -= count;

            if (cnt == 0) {
                break;
            }
        }

        if (sum % 2 == 0) {
            return sum;
        }

        int maxWhenChangeEven = 0, maxWhenChangeOdd = 0;
        if (minEven > 0 && !oddList.isEmpty()) {
            maxWhenChangeEven = sum - minEven + oddList.getFirst();
        }

        if (minOdd > 0 && !evenList.isEmpty()) {
            maxWhenChangeOdd = sum - minOdd + evenList.getFirst();
        }

        return Math.max(maxWhenChangeEven, maxWhenChangeOdd);
    }
}