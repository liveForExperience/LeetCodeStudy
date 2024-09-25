package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/8/1 13:16
 */
public class LeetCode_705_1_设计哈希集合 {
    class MyHashSet {
        int[] bucket;
        public MyHashSet() {
            bucket = new int[1000001];
        }

        public void add(int key) {
            bucket[key] = 1;
        }

        public void remove(int key) {
            bucket[key] = 0;
        }

        public boolean contains(int key) {
            return bucket[key] == 1;
        }
    }
}
