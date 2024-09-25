package com.bottomlord.week_031;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/5 19:43
 */
public class LeetCode_146_1_LRU缓存机制 {
    class LRUCache extends LinkedHashMap<Integer, Integer>{
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
}
