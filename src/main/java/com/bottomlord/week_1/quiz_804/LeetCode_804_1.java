package com.bottomlord.week_1.quiz_804;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/9 12:50
 */
public class LeetCode_804_1 {
    public int uniqueMorseRepresentations(String[] words) {
        String[] dict = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String word: words) {
            StringBuilder sb = new StringBuilder();
            for (char c: word.toCharArray()) {
                sb.append(dict[c - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
