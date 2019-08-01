package com.bottomlord.week_4;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/8/1 10:24
 */
public class LeetCode_706_2 {
    class MyHashMap {
        Integer[] bucket;
        public MyHashMap() {
            this.bucket = new Integer[1000001];
        }

        public void put(int key, int value) {
            this.bucket[key] = value;
        }

        public int get(int key) {
            return this.bucket[key] != null ? this.bucket[key] : -1;
        }

        public void remove(int key) {
            this.bucket[key] = -1;
        }
    }
}
