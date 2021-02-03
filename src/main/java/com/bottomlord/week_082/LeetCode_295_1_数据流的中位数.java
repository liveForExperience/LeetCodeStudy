package com.bottomlord.week_082;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/2/3 9:40
 */
public class LeetCode_295_1_数据流的中位数 {
    class MedianFinder {
        private PriorityQueue<Integer> maxHeap, minHeap;
        private int count;
        public MedianFinder() {
            this.count = 0;
            this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            this.minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            count++;
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());

            if ((count & 1) != 0) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (count == 0) {
                return 0;
            }

            return (count & 1) != 0 ? (double)maxHeap.peek() : ((double)maxHeap.peek() + (double)minHeap.peek()) / 2;
        }
    }
}
