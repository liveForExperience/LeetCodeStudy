package com.bottomlord.week_111;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2021-08-27 08:45:11
 */
public class LeetCode_295_1_数据流的中位数 {
    class MedianFinder {
        private PriorityQueue<Integer> bigger, smaller;
        public MedianFinder() {
            bigger = new PriorityQueue<>();
            smaller = new PriorityQueue<>(Comparator.reverseOrder());
        }

        public void addNum(int num) {
            if (bigger.isEmpty()) {
                bigger.offer(num);
                return;
            }

            if (num < bigger.peek()) {
                smaller.offer(num);
            } else {
                bigger.offer(num);
            }

            if (smaller.size() > bigger.size()) {
                bigger.offer(smaller.poll());
                return;
            }

            if (smaller.size() + 1 < bigger.size()) {
                smaller.offer(bigger.poll());
            }
        }

        public double findMedian() {
            if (bigger.isEmpty() && smaller.isEmpty()) {
                return 0;
            }

            if (bigger.size() == smaller.size()) {
                return (1D * bigger.peek() + smaller.peek()) / 2;
            }

            return (double) bigger.peek();
        }
    }

}
