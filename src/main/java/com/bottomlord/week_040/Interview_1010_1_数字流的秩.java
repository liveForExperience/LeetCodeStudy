package com.bottomlord.week_040;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/4/7 8:00
 */
public class Interview_1010_1_数字流的秩 {
    class StreamRank {
        private PriorityQueue<Integer> queue;
        public StreamRank() {
            this.queue = new PriorityQueue<>();
        }

        public void track(int x) {
            this.queue.offer(x);
        }

        public int getRankOfNumber(int x) {
            int count = 0;
            List<Integer> list = new ArrayList<>();
            while (!this.queue.isEmpty()) {
                if (this.queue.peek() > x) {
                    break;
                }

                list.add(this.queue.poll());
                count++;
            }

            for (int num : list) {
                this.queue.offer(num);
            }

            return count;
        }
    }
}
