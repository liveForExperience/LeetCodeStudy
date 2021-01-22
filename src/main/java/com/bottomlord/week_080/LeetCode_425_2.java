package com.bottomlord.week_080;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/1/22 16:01
 */
public class LeetCode_425_2 {
    public List<List<String>> wordSquares(String[] words) {
        int len = words[0].length();
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len - 1; i++) {
                sb.append(word.charAt(i));
                String prefix = sb.toString();
                List<String> list = map.getOrDefault(prefix, new ArrayList<>());
                list.add(word);
                map.put(prefix, list);
            }
        }

        for (String word : words) {
            backTrack(1, len, new ArrayList<String>(){{this.add(word);}}, ans, map);
        }

        return ans;
    }

    private void backTrack(int index, int len, List<String> wordList, List<List<String>> ans, Map<String, List<String>> map) {
        if (index == len) {
            ans.add(new ArrayList<>(wordList));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String word : wordList) {
            sb.append(word.charAt(index));
        }
        String prefix = sb.toString();

        if (!map.containsKey(prefix)) {
            return;
        }

        for (String word : map.get(prefix)) {
            wordList.add(word);
            backTrack(index + 1, len, wordList, ans, map);
            wordList.remove(wordList.size() - 1);
        }
    }
}
