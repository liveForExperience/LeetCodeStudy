package com.bottomlord.week_191;

/**
 * @author chen yue
 * @date 2023-03-12 21:15:05
 */
public class LeetCode_900_1_RLE迭代器 {
    static class RLEIterator {
        private int index = 0;
        private int[] encoding;

        public RLEIterator(int[] encoding) {
            this.encoding = encoding;
        }

        public int next(int n) {
            while (n > 0) {
                if (encoding[index] >= n) {
                    encoding[index] -= n;
                    return encoding[index + 1];
                } else {
                    n -= encoding[index];
                    index += 2;

                    if (index >= encoding.length) {
                        return -1;
                    }
                }
            }

            return -1;
        }
    }
}
