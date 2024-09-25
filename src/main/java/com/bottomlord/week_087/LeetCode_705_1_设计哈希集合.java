package com.bottomlord.week_087;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author ChenYue
 * @date 2021/3/13 14:16
 */
public class LeetCode_705_1_设计哈希集合 {
    class MyHashSet {
        private static final int BASE = 769;
        private final LinkedList<Integer>[] bucket;

        @SuppressWarnings("unchecked")
        public MyHashSet() {
            bucket = new LinkedList[BASE];
            for (int i = 0; i < BASE; i++) {
                bucket[i] = new LinkedList<>();
            }
        }

        public void add(int key) {
            LinkedList<Integer> list = bucket[hash(key)];
            list.addLast(key);
        }

        public void remove(int key) {
            LinkedList<Integer> list = bucket[hash(key)];
            list.removeIf(integer -> integer == key);
        }

        public boolean contains(int key) {
            LinkedList<Integer> list = bucket[hash(key)];
            for (Integer integer : list) {
                if (integer == key) {
                    return true;
                }
            }

            return false;
        }

        private int hash(int key) {
            return key % BASE;
        }
    }
}
