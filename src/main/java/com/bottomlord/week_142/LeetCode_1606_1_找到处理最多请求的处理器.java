package com.bottomlord.week_142;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-03-30 08:47:42
 */
public class LeetCode_1606_1_找到处理最多请求的处理器 {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requests = new int[k];
        TreeSet<Integer> available = new TreeSet<>();
        Queue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(x -> x[0])) ;

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        int max = 0;
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] busyServer = busy.poll();
                available.add(busyServer[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            Integer server = available.ceiling(i % k);
            if (server == null) {
                server = available.first();
            }
            available.remove(server);

            busy.offer(new int[]{arrival[i] + load[i], server});
            requests[server]++;
            max = Math.max(max, requests[server]);
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
