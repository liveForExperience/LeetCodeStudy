package com.bottomlord.week_029;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/1/22 9:36
 */
public class LeetCode_380_1_常数时间插入删除和获取随机元素 {
    class RandomizedSet {
        private Random random;
        private List<Integer> list;
        private Map<Integer, Integer> map;

        public RandomizedSet() {
            this.random = new Random();
            this.list = new ArrayList<>();
            this.map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }

            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            int index = map.get(val);
            int last = list.get(list.size() - 1);
            list.set(index, last);
            map.put(last, index);

            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
