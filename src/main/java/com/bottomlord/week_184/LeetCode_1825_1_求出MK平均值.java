package com.bottomlord.week_184;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author chen yue
 * @date 2023-01-18 14:54:31
 */
public class LeetCode_1825_1_求出MK平均值 {
    static class MKAverage {

        private TreeMap<Integer, Integer> s1 = new TreeMap<>(),
        s2 = new TreeMap<>(), s3 = new TreeMap<>();
        private int sum = 0, n1 = 0, n3 = 0, m, k;
        private Queue<Integer> queue = new ArrayDeque<>();

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {
            queue.offer(num);

            if (queue.size() < m) {
                s2.put(num, s2.getOrDefault(num, 0) + 1);
                sum += num;
                return;
            }

            while (n1 < k) {
                int firstNum = s2.firstKey();
                s2.put(firstNum, s2.get(firstNum) - 1);
                if (s2.get(firstNum) == 0) {
                    s2.remove(firstNum);
                }
                sum -= firstNum;
                s1.put(firstNum, s1.getOrDefault(firstNum, 0) + 1);
                n1++;
            }

            while (n3 < k) {
                int lastNum = s2.lastKey();
                s2.put(lastNum, s2.get(lastNum) - 1);
                if (s2.get(lastNum) == 0) {
                    s2.remove(lastNum);
                }
                sum -= lastNum;
                s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
                n3++;
            }

            if (num < s1.lastKey()) {
                s1.put(num, s1.getOrDefault(num, 0) + 1);
                int lastNum = s1.lastKey();
                s1.put(lastNum, s1.get(lastNum) - 1);
                if (s1.get(lastNum) == 0) {
                    s1.remove(lastNum);
                }

                s2.put(lastNum, s2.getOrDefault(lastNum, 0) + 1);
                sum += lastNum;
            } else if (num > s3.firstKey()) {
                s3.put(num, s3.getOrDefault(num, 0) + 1);
                int firstNum = s3.firstKey();
                s3.put(firstNum, s3.get(firstNum) - 1);
                if (s3.get(firstNum) == 0) {
                    s3.remove(firstNum);
                }

                s2.put(firstNum, s2.getOrDefault(firstNum, 0) + 1);
                sum += firstNum;
            } else {
                s2.put(num, s2.getOrDefault(num, 0) + 1);
                sum += num;
            }

            if (queue.isEmpty() || queue.size() == m) {
                return;
            }

            int pollNum = queue.poll();
            if (s1.containsKey(pollNum)) {
                s1.put(pollNum, s1.get(pollNum) - 1);
                if (s1.get(pollNum) == 0) {
                    s1.remove(pollNum);
                }

                int firstNum = s2.firstKey();
                s1.put(firstNum, s1.getOrDefault(firstNum, 0) + 1);
                s2.put(firstNum, s2.get(firstNum) - 1);
                if (s2.get(firstNum) == 0) {
                    s2.remove(firstNum);
                }
                sum -= firstNum;
            } else if (s3.containsKey(pollNum)) {
                s3.put(pollNum, s3.get(pollNum) - 1);
                if (s3.get(pollNum) == 0) {
                    s3.remove(pollNum);
                }

                int lastNum = s2.lastKey();
                s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
                s2.put(lastNum, s2.get(lastNum) - 1);
                if (s2.get(lastNum) == 0) {
                    s2.remove(lastNum);
                }
                sum -= lastNum;
            } else {
                sum -= pollNum;
                s2.put(pollNum, s2.get(pollNum) - 1);
                if (s2.get(pollNum) == 0) {
                    s2.remove(pollNum);
                }
            }
        }

        public int calculateMKAverage() {
            return queue.size() < m ? -1 : sum / (m - 2 * k);
        }
    }
}
