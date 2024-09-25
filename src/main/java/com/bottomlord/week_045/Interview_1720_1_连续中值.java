package com.bottomlord.week_045;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/5/17 19:12
 */
public class Interview_1720_1_连续中值 {
    static class MedianFinder {
        private PriorityQueue<Double> maxHeap;
        private PriorityQueue<Double> minHeap;
        public MedianFinder() {
            this.minHeap = new PriorityQueue<>();
            this.minHeap.offer((double)Integer.MAX_VALUE);
            this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            this.maxHeap.offer((double)Integer.MIN_VALUE);
        }

        public void addNum(int num) {
            double min = maxHeap.peek();
            if (num <= min) {
                maxHeap.offer((double) num);
            } else {
                minHeap.offer((double) num);
            }

            if (maxHeap.size() >= minHeap.size() + 2) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()){
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                return maxHeap.peek();
            }
        }
    }
}
