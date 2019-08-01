package com.bottomlord.week_4;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/8/1 9:57
 */
public class LeetCode_706_1_设计哈希映射 {
    class MyHashMap {
        int[] bucket;
        public MyHashMap() {
            this.bucket = new int[1000001];
            Arrays.fill(this.bucket, -1);
        }

        public void put(int key, int value) {
            this.bucket[key] = value;
        }

        public int get(int key) {
            return this.bucket[key];
        }

        public void remove(int key) {
            this.bucket[key] = -1;
        }
    }
}
