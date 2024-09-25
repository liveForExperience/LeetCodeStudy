package com.bottomlord.week_093;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/20 10:02
 */
public class LeetCode_527_2 {
    public List<String> wordsAbbreviation(List<String> dict) {
        TreeMap<String, List<String>> map = new TreeMap<>((x, y) -> {
            if (x.length() != y.length()) {
                return x.length() - y.length();
            }

            return x.compareTo(y);
        });

        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                String abbreviation = getAbbreviation(word, i);
                List<String> list = map.getOrDefault(abbreviation, new ArrayList<>());
                list.add(word);
                map.put(abbreviation, list);
            }
        }


        Map<String, String> mapping = new HashMap<>();

        for (String abbreviation : map.keySet()) {
            if (map.get(abbreviation).size() == 1) {
                String word = map.get(abbreviation).get(0);
                if (mapping.containsKey(word)) {
                    continue;
                }

                if (abbreviation.length() < word.length()) {
                    mapping.put(word, abbreviation);
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (String word : dict) {
            ans.add(mapping.getOrDefault(word, word));
        }

        return ans;
    }

    private String getAbbreviation(String word, int index) {
        String abbreviation = word.substring(0, index + 1) + (word.length() - index - 2 == 0 ? "" : (word.length() - index - 2));
        String suffix;
        if (index < word.length() - 1) {
            suffix = "" + word.charAt(word.length() - 1);
        } else if (index == word.length() - 1) {
            suffix = "" + word.charAt(word.length() - 1);
        } else {
            suffix = "";
        }
        return abbreviation + suffix;
    }
}
