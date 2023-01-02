package com.bottomlord.week_182;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-01-02 19:23:26
 */
public class LeetCode_1801_1_积压订单中的订单总数 {
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buyQ = new PriorityQueue<>((x, y) -> y[0] - x[0]),
                             sellQ = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));

        for (int[] order : orders) {
            int price = order[0], type = order[2];

            if (type == 0) {
                while (!sellQ.isEmpty()) {
                    int[] sell = sellQ.peek();
                    if (sell[0] > price) {
                        break;
                    }

                    int minCost = Math.min(sell[1], order[1]);
                    order[1] -= minCost;
                    sell[1] -= minCost;

                    if (sell[1] == 0) {
                        sellQ.poll();
                    }

                    if (order[1] == 0) {
                        break;
                    }
                }

                if (order[1] > 0) {
                    buyQ.offer(order);
                }
            } else {
                while (!buyQ.isEmpty()) {
                    int[] buy = buyQ.peek();
                    if (buy[0] < price) {
                        break;
                    }

                    int minCost = Math.min(buy[1], order[1]);
                    order[1] -= minCost;
                    buy[1] -= minCost;

                    if (buy[1] == 0) {
                        buyQ.poll();
                    }

                    if (order[1] == 0) {
                        break;
                    }
                }

                if (order[1] > 0) {
                    sellQ.offer(order);
                }
            }
        }

        int mod = 1000000009, ans = 0;
        while (!buyQ.isEmpty()) {
            ans += buyQ.poll()[1];
            ans %= mod;
        }

        while (!sellQ.isEmpty()) {
            ans += sellQ.poll()[1];
            ans %= mod;
        }

        return ans;
    }
}
