package com.bottomlord.week_040;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/4/8 7:56
 */
public class Interview_1602_1_单词频率 {
    class WordsFrequency {
        private Map<String, Integer> map;
        public WordsFrequency(String[] book) {
            map = new HashMap<>();
            for (String word : book) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        public int get(String word) {
            return map.getOrDefault(word, 0);
        }
    }
}
