package com.bottomlord.week_142;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-03-30 10:29:47
 */
public class LeetCode_1606_2 {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requests = new int[k];
        Queue<Integer> available = new PriorityQueue<>();
        Queue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < k; i++) {
            available.offer(i);
        }

        int max = 0;
        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] busyServer = busy.poll();
                available.offer(i + (k - (i % k - busyServer[1])) % k);
            }

            if (available.isEmpty()) {
                continue;
            }

            int server = available.poll() % k;
            requests[server]++;
            busy.offer(new int[]{arrival[i] + load[i], server});
            max = Math.max(requests[server], max);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < requests.length; i++) {
            if (requests[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}