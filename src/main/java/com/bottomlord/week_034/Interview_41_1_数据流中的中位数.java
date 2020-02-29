package com.bottomlord.week_034;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ThinkPad
 * @date 2020/2/29 15:43
 */
public class Interview_41_1_数据流中的中位数 {
    class MedianFinder {
        private PriorityQueue<Double> lH;
        private PriorityQueue<Double> sH;
        private int size;

        public MedianFinder() {
            this.lH = new PriorityQueue<>();
            this.sH = new PriorityQueue<>(Comparator.reverseOrder());
            this.size = 0;
        }

        public void addNum(int num) {
            double dNum = num;
            if (!sH.isEmpty()) {
                sH.offer(dNum);
                dNum = sH.poll();
            }
            lH.offer(dNum);
            size++;
        }

        public double findMedian() {
            for (int i = 0; i < size / 2 - sH.size(); i++) {
                sH.offer(lH.poll());
            }

            return (size & 1) == 1 ? lH.peek() : (sH.peek() + lH.peek()) / 2;
        }
    }
}
