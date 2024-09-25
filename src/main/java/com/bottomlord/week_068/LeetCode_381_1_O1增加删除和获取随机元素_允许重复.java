package com.bottomlord.week_068;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/11/1 21:01
 */
public class LeetCode_381_1_O1增加删除和获取随机元素_允许重复 {
    class RandomizedCollection {
        Map<Integer, Set<Integer>> map;
        List<Integer> list;
        public RandomizedCollection() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
        }

        public boolean insert(int val) {
            list.add(val);
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(list.size() - 1);
            map.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            Iterator<Integer> iterator = map.get(val).iterator();
            int index = iterator.next();
            int lastNumIndex = list.size() - 1;
            list.set(index, list.get(lastNumIndex));
            map.get(val).remove(index);
            map.get(list.get(lastNumIndex)).remove(lastNumIndex);

            if (index < lastNumIndex) {
                map.get(list.get(lastNumIndex)).add(index);
            }

            if (map.get(val).size() == 0) {
                map.remove(val);
            }

            list.remove(lastNumIndex);

            return true;
        }

        public int getRandom() {
            return list.get((int)(Math.random() * list.size()));
        }
    }
}
