package com.bottomlord.week_132;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author chen yue
 * @date 2022-01-23 12:17:43
 */
public class LeetCode_2034_1_股票价格波动 {
    class StockPrice {
        private int maxTimestamp;
        private Map<Integer, Integer> map;
        private TreeMap<Integer, Integer> countMap;

        public StockPrice() {
            this.maxTimestamp = 0;
            this.map = new HashMap<>();
            this.countMap = new TreeMap<>();
        }

        public void update(int timestamp, int price) {
            maxTimestamp = Math.max(timestamp, maxTimestamp);
            Integer prePrice = map.get(timestamp);
            map.put(timestamp, price);

            if (prePrice != null) {
                countMap.put(prePrice, countMap.getOrDefault(prePrice, 0) - 1);
                if (countMap.get(prePrice) == 0) {
                    countMap.remove(prePrice);
                }
            }

            countMap.put(price, countMap.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return map.get(maxTimestamp);
        }

        public int maximum() {
            return countMap.lastKey();
        }

        public int minimum() {
            return countMap.firstKey();
        }
    }
}
