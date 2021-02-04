package com.bottomlord.week_082;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/2/3 8:55
 */
public class LeetCode_480_1_滑动窗口中位数 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length - k + 1 <= 0) {
            return new double[0];
        }

        double[] ans = new double[nums.length - k + 1];
        MedianFinder medianFinder = new MedianFinder();
        for (int i = 0; i < k; i++) {
            medianFinder.add(nums[i]);
        }

        ans[0] = medianFinder.median();

        for (int i = 1; i < ans.length; i++) {
            medianFinder.add(nums[i + k - 1]);
            medianFinder.del(nums[i - 1]);
            ans[i] = medianFinder.median();
        }

        return ans;
    }

    static class MedianFinder {
        private PriorityQueue<Integer> minHeap, maxHeap;
        private Map<Integer, Integer> freq;
        private int count;

        public MedianFinder (){
            minHeap = new PriorityQueue<>(Comparator.reverseOrder());
            maxHeap = new PriorityQueue<>();
            freq = new HashMap<>();
            count = 0;
        }

        public void add(int num) {
            count++;
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
            if (count % 2 == 1) {
                minHeap.offer(maxHeap.poll());
            }
        }

        public void del(int num) {
            if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                return;
            }

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            if (!minHeap.isEmpty() && Objects.equals(num, minHeap.peek())) {
                minHeap.poll();
                if (count % 2 == 0) {
                    minHeap.offer(maxHeap.poll());
                }

                freq.put(num, freq.get(num) - 1);
                if (freq.get(num) == 0) {
                    freq.remove(num);
                }
            } else if (!maxHeap.isEmpty() && Objects.equals(num, maxHeap.peek())) {
                maxHeap.poll();
                if (count % 2 == 1) {
                    maxHeap.offer(minHeap.poll());
                }

                freq.put(num, freq.get(num) - 1);
                if (freq.get(num) == 0) {
                    freq.remove(num);
                }
            } else if (!minHeap.isEmpty() && num < minHeap.peek()) {
                if (count % 2 == 0) {
                    minHeap.offer(maxHeap.poll());
                }
            } else {
                if (count % 2 == 1) {
                    maxHeap.offer(minHeap.poll());
                }
            }

            boolean hasRemove = true;
            Set<Integer> set = new HashSet<>();
            while (hasRemove) {
                hasRemove = false;
                for (Integer key : freq.keySet()) {
                    if (set.contains(key)) {
                        continue;
                    }

                    boolean hasRemoveCurrent = false;
                    if (!minHeap.isEmpty() && Objects.equals(key, minHeap.peek())) {
                        minHeap.poll();
                        hasRemove = true;
                        hasRemoveCurrent = true;
                    } else if (!maxHeap.isEmpty() && Objects.equals(key, maxHeap.peek())) {
                        maxHeap.poll();
                        hasRemove = true;
                        hasRemoveCurrent = true;
                    }

                    if (hasRemoveCurrent) {
                        freq.put(key, freq.get(key) - 1);
                        if (Objects.equals(freq.get(key), 0)) {
                            set.add(key);
                        }
                    }
                }
            }

            for (Integer key : set) {
                freq.remove(key);
            }

            count--;
        }

        public double median() {
            if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                return 0;
            }

            return count % 2 == 0 ? ((double)minHeap.peek() + (double)maxHeap.peek()) / 2 : (double)minHeap.peek();
        }
    }
}
